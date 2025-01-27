package com.example.springboot.elasticsearch.controller;

import com.example.springboot.elasticsearch.bean.Employee;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yinchao
 * @ClassName: EmployeevController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/1/26 23:30
 */
@RestController
public class EmployeeV2Controller {

    private static final String index = "employee_v1";

    private final ExecutorService executors = Executors.newFixedThreadPool(20);

    private static final int BATCH_SIZE = 100000;

    @Resource
    private RestHighLevelClient restHighLevelClient;


    @GetMapping("/scrollEmployee")
    public int scrollEmployee() throws IOException {
        List<Employee> products = new ArrayList<>();
        // 初始化搜索请求
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery()); // 查询所有数据
        searchSourceBuilder.size(1000); // 每批获取 1000 条数据
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L)); // 设置游标过期时间为 1 分钟
        // 第一次查询
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHit[] hits = searchResponse.getHits().getHits();

        // 处理第一批数据
        while (hits != null && hits.length > 0) {
            for (SearchHit hit : hits) {
                // 将 JSON 数据转换为实体类
                Employee product = mapToProduct(hit);
                products.add(product);
            }
            // 获取下一批数据
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(TimeValue.timeValueMinutes(1L)); // 保持游标活跃
            searchResponse = restHighLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            hits = searchResponse.getHits().getHits();
        }
        // 清除游标
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        return products.size();
    }

    @GetMapping("/slowQuery")
    public String slowQuery() throws IOException {
        // 创建搜索模板请求
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest(index));
        // 定义脚本
        String script = "{" +
                "\"query\": {" +
                "  \"bool\": {" +
                "    \"must\": [" +
                "      {" +
                "        \"script\": {" +
                "          \"script\": {" +
                "            \"source\": \"long total = 0; for (int i = 0; i < 1000000; i++) { total += i; }\"," +
                "            \"lang\": \"painless\"" +
                "          }" +
                "        }" +
                "      }" +
                "    ]" +
                "  }" +
                "}" +
                "}";
        request.setScriptType(ScriptType.INLINE);
        request.setScript(script);
        request.setScriptParams(Collections.emptyMap());
        // 执行搜索模板请求
        SearchTemplateResponse response = restHighLevelClient.searchTemplate(request, RequestOptions.DEFAULT);
        return response.getResponse().toString();
    }

    @GetMapping("/clearScroll")
    public void testScroll() throws IOException {
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(null);
        restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
    }

    @PostMapping("/batchSaveEmployee")
    public void batchSaveEmployee() {
        // 使用线程池批量保存数据
        for (int i = 0; i < 20; i++) {
            final int batch = i;
            executors.submit(() -> batchEmployee(batch));
        }
    }

    private Employee mapToProduct(SearchHit hit) {
        Employee employee = new Employee();
        employee.setId(Long.parseLong(hit.getId()));
        employee.setName((String) hit.getSourceAsMap().get("name"));
        employee.setPassword((String) hit.getSourceAsMap().get("password"));
        return employee;
    }

    private void batchEmployee(long batch) {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            Employee employee = new Employee();
            employee.setId(BATCH_SIZE * batch + i);
            employee.setName("sys" + employee.getId());
            employee.setPassword("123");
            list.add(employee);
            if (list.size() == 1000) {
                saveEmployeeToEs(list);
                list.clear();
            }
        }


    }

    private void saveEmployeeToEs(List<Employee> list) {
        BulkRequest bulkRequest = new BulkRequest();
        for (Employee employee : list) {
            String docId = String.valueOf(employee.getId());
            @SuppressWarnings("unchecked")
            IndexRequest updateRequest = new IndexRequest(index)
                    .id(docId)
                    .source(convertMap(employee), XContentType.JSON);
            bulkRequest.add(updateRequest);
        }

        // 执行批量更新请求
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 处理响应
        if (!bulkResponse.hasFailures()) {
            System.out.println("Bulk update successful for records");
        } else {
            System.err.println("Bulk update had failures for records,error= " + bulkResponse.buildFailureMessage());
        }
    }

    private Map<String, Object> convertMap(Employee employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", employee.getName());
        map.put("password", employee.getPassword());
        map.put("id", employee.getId());
        return map;
    }
}
