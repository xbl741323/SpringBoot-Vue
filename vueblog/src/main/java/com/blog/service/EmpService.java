package com.blog.service;

import com.blog.bean.Employee;

import java.util.Collection;


public interface EmpService {

    //获取所有员工信息
    public Collection<Employee> getEmp();

    //添加员工
    public void addEmp(Employee employee);

    //编辑员工
    public void editEmp(Employee employee);

    //根据id删除员工
    public void deleteEmp(Employee employee);
}
