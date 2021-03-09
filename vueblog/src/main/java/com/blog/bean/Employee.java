package com.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

//员工表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String empName;
    private Integer age;
    private Integer sex; //0女 1男
    private Integer deptId;

    @Override
    public String toString() {
        return "员工信息：" +
                "id=" + id +
                ", empName='" + empName +
                ", age=" + age +
                ", sex=" + sex +
                ", deptId=" + deptId +
                '}';
    }
}
