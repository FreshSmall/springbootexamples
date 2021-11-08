package com.example.demo;

import com.example.demo.example.ServiceDependency;
import com.example.demo.example.SomeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 */
@SpringBootApplication
public class MySpringApp {

    @Bean
    ServiceDependency dependency() {
        return new ServiceDependency();
    }

    @Bean
    SomeService someService(ServiceDependency dependency) {
        return new SomeService(dependency);
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(MySpringApp.class, args)));
    }
}
