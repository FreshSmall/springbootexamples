package com.example.springboot.elasticsearch;

import com.example.springboot.elasticsearch.bean.Employee;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootElasticsearchApplication.class)
@Slf4j
class SpringbootElasticsearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void createIndex() throws IOException {
        /*Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("sys");
        employee.setPassword("123");*/
        BulkRequest request = new BulkRequest();
        UpdateRequest updateRequest = new UpdateRequest("post","2");
        updateRequest.doc(XContentType.JSON,"other", "test");
        updateRequest.docAsUpsert(true);
        request.add(updateRequest);
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        for (BulkItemResponse resp : responses) {
            if (null != resp.getFailure() && log.isErrorEnabled()) {
                log.error("[es] bulk update error, {}", resp.getFailureMessage());
            }
        }
    }

}
