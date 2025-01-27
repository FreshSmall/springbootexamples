//package com.example.springboot.elasticsearch.controller;
//
//import com.example.springboot.elasticsearch.bean.Employee;
//import com.example.springboot.elasticsearch.repository.EmployeeRepository;
//import org.elasticsearch.action.search.ClearScrollRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchScrollRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author: yinchao
// * @ClassName: EmployeeController
// * @Description:
// * @team wuhan operational dev.
// * @date: 2025/1/25 23:13
// */
//@RestController
//public class EmployeeController {
//
//    // 创建一个线程池对象
//    private ExecutorService executors = Executors.newFixedThreadPool(20);
//
//    private static final int BATCH_SIZE = 100000;
//
//    @Resource
//    private EmployeeRepository employeeRepository;
//
//    @Resource
//    private RestHighLevelClient restHighLevelClient;
//
//    @PostMapping("/saveEmployee")
//    public void saveEmployee() {
//        Employee employee = new Employee();
//        employee.setId(1L);
//        employee.setName("sys");
//        employee.setPassword("123");
//        employeeRepository.save(employee);
//    }
//
//    @PostMapping("/batchSaveEmployee")
//    public void batchSaveEmployee() {
//        // 使用线程池批量保存数据
//        for (int i = 0; i < 20; i++) {
//            final int batch = i;
//            executors.submit(() -> batchEmployee(batch));
//        }
//    }
//
//    @GetMapping("/queryEmployee")
//    public Iterable<Employee> queryEmployee() {
//        final List<Long> idList = new ArrayList<>();
//        idList.add(1L);
//        idList.add(2L);
//        idList.add(3L);
//        idList.add(4L);
//        idList.add(5L);
//        idList.add(6L);
//        idList.add(7L);
//        idList.add(8L);
//        idList.add(9L);
//        idList.add(10L);
//        idList.add(11L);
//        idList.add(12L);
//        idList.add(13L);
//        idList.add(14L);
//        idList.add(15L);
//        return employeeRepository.findAllById(idList);
//    }
//
//    @GetMapping(value = "/queryEmployeeByName")
//    public Employee queryEmployeeByName(@RequestParam String name) {
//        return employeeRepository.queryEmployeeByName(name);
//    }
//
//
//    @GetMapping("/queryEmployeeByPage")
//    public Page<Employee> getProductsByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return employeeRepository.findAll(pageable);
//    }
//
//    @GetMapping("/scrollEmployee")
//    public int scrollEmployee() throws IOException {
//        List<Employee> products = new ArrayList<>();
//        String index = "employee";
//        // 初始化搜索请求
//        SearchRequest searchRequest = new SearchRequest(index);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery()); // 查询所有数据
//        searchSourceBuilder.size(1000); // 每批获取 1000 条数据
//        searchRequest.source(searchSourceBuilder);
//        searchRequest.scroll(TimeValue.timeValueMinutes(1L)); // 设置游标过期时间为 1 分钟
//
//        // 第一次查询
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        String scrollId = searchResponse.getScrollId();
//        SearchHit[] hits = searchResponse.getHits().getHits();
//
//        // 处理第一批数据
//        while (hits != null && hits.length > 0) {
//            for (SearchHit hit : hits) {
//                // 将 JSON 数据转换为实体类
//                Employee product = mapToProduct(hit);
//                products.add(product);
//            }
//            // 获取下一批数据
//            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
//            scrollRequest.scroll(TimeValue.timeValueMinutes(1L)); // 保持游标活跃
//            searchResponse = restHighLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);
//            scrollId = searchResponse.getScrollId();
//            hits = searchResponse.getHits().getHits();
//        }
//        // 清除游标
//        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
//        clearScrollRequest.addScrollId(scrollId);
//        restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
//        return products.size();
//    }
//
//    private Employee mapToProduct(SearchHit hit) {
//        Employee employee = new Employee();
//        employee.setId(Long.parseLong(hit.getId()));
//        employee.setName((String) hit.getSourceAsMap().get("name"));
//        employee.setPassword((String) hit.getSourceAsMap().get("password"));
//        return employee;
//    }
//
//    private void batchEmployee(long batch) {
//        List<Employee> list = new ArrayList<>();
//        for (int i = 0; i < BATCH_SIZE; i++) {
//            Employee employee = new Employee();
//            employee.setId(BATCH_SIZE * batch + i);
//            employee.setName("sys" + employee.getId());
//            employee.setPassword("123");
//            list.add(employee);
//            if (list.size() == 1000) {
//                employeeRepository.saveAll(list);
//                list.clear();
//            }
//        }
//
//
//    }
//}
