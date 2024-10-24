package com.example.springbootwebsimple.dto;

/**
 * @author: yinchao
 * @ClassName: StudentDto
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/8/11 16:59
 */
public class StudentDto {

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
