package com.example.springboot.cache.repository;

import com.example.springboot.cache.bean.Employee;
import com.example.springboot.cache.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author: yinchao
 * @ClassName: EmployeeDao
 * @Description: 雇员数据层
 * @team wuhan operational dev.
 * @date: 2022/9/20 11:20
 */
@Service
public class EmployeeDao {

    Logger LOG = LoggerFactory.getLogger(EmployeeDao.class);


    public Employee getEmpById(Integer id) {
        LOG.info("开始查询雇员信息。id:{}",id);
        if (id == 12) {
            return new Employee(id,"bob","ycsuper2819@gmail.com",1,12);
        }else{
            return null;
        }
    }
}
