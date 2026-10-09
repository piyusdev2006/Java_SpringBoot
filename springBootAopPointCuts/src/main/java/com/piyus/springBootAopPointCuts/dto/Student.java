package com.piyus.springBootAopPointCuts.dto;

import jdk.jfr.Timestamp;

@Timestamp
public class Student {
    private String name;
    private Integer age;

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
}