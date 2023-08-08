package com.example.springbootmybatisplus.config;

import com.example.springbootmybatisplus.interceptor.MyInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: yinchao
 * @ClassName: MybatisPlusConfig
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/7/20 09:52
 */
//@Component
public class MyDataSourceInterceptorConfig /*implements ApplicationListener<ContextRefreshedEvent>*/ {

    /*@Autowired
    private MyInterceptor mybatisInterceptor;

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactories;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (SqlSessionFactory factory : sqlSessionFactories) {
            factory.getConfiguration().addInterceptor(mybatisInterceptor);
        }
    }*/
}
