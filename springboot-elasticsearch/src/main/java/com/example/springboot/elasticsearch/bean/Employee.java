package com.example.springboot.elasticsearch.bean;


import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 *  Employee
 * </pre>
 *
 * <pre>
 * @author mazq
 * 修改记录
 *    修改后版本:     修改人：  修改日期: 2020/07/16 17:02  修改内容:
 * </pre>
 */
@Data
public class Employee implements Serializable {
    private Long id;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
