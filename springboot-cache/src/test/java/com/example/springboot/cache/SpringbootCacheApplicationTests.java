package com.example.springboot.cache;

import com.example.springboot.cache.bean.Employee;
import com.example.springboot.cache.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class SpringbootCacheApplicationTests {

    private final CountDownLatch countDownLatch = new CountDownLatch(5);

    @Autowired
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (finalI %2==0) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    employeeService.getEmp(12);
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("测试完成");
    }

}
