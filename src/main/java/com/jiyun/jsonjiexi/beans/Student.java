package com.jiyun.jsonjiexi.beans;

/**
 * Created by 刘建康 on 2019/6/25.
 */

public class Student {
    private String code;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Student() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String code, String name) {

        this.code = code;
        this.name = name;
    }
}
