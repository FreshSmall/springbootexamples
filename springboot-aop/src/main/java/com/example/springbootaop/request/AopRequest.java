package com.example.springbootaop.request;

/**
 * @author: yinchao
 * @ClassName: AopRequest
 * @Description: 请求参数
 * @team wuhan operational dev.
 * @date: 2022/9/22 09:37
 */
public class AopRequest {

    private String name;
    private int age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
