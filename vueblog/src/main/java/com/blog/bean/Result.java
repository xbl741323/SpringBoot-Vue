package com.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//返回结果类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;

    @Override
    public String toString() {
        return "{" +
                "code：" + code +
                ", msg：'" + msg +
                '}';
    }
}
