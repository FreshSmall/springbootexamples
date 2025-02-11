package com.example.springboot.elasticsearch;

import com.example.springboot.elasticsearch.bean.Employee;
import com.example.springboot.elasticsearch.repository.EmployeeRepository;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootElasticsearchApplication.class)
class SpringbootElasticsearchApplicationTests {

    @Resource
    private EmployeeRepository employeeRepository;

    @Test
    void createIndex() throws IOException {
        /*Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("sys");
        employee.setPassword("123");*/

    }

    @Test
    public void testSave() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("sys");
        employee.setPassword("123");
        employeeRepository.save(employee);
    }

}
