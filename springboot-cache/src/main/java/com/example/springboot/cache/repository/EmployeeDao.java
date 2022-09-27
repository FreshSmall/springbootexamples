package com.example.springboot.cache.repository;

import com.example.springboot.cache.bean.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yinchao
 * @ClassName: EmployeeDao
 * @Description: 雇员数据层
 * @team wuhan operational dev.
 * @date: 2022/9/20 11:20
 */
@Service
public class EmployeeDao {

    private final static AtomicInteger atomicInteger = new AtomicInteger(0);

    Logger LOG = LoggerFactory.getLogger(EmployeeDao.class);


    public Employee getEmpById(Integer id) {
        LOG.info("开始查询雇员信息。id:{},count:{}",id,atomicInteger.addAndGet(1));
        if (id == 12) {
            return new Employee(id,"bob","ycsuper2819@gmail.com",1,12);
        }else{
            return null;
        }
    }
}
