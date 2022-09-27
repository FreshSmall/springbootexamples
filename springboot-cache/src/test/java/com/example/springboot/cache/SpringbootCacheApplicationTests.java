package com.example.springboot.cache;

import com.example.springboot.cache.bean.Employee;
import com.example.springboot.cache.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class SpringbootCacheApplicationTests {

    private final CountDownLatch countDownLatch = new CountDownLatch(100);

    @Autowired
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
        /*for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    employeeService.getEmp(12);
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Employee emp1 = employeeService.getEmp(12);
        System.out.println(emp1.toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Employee emp = employeeService.getEmp(12);
        System.out.println(emp.toString());

        Employee emp2 = employeeService.getEmp(12);
        System.out.println(emp2.toString());

        System.out.println("测试完成");
    }

}
