package com.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

//返回结果类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Collection<Employee> list;
    private Integer total;

    @Override
    public String toString() {
        return "{" +
                "code：" + code +
                ", msg：'" + msg +
                '}';
    }
}
