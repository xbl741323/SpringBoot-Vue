package com.blog.mapper;

import com.blog.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EmpMapper {

    //获取所有员工信息
    public Collection<Employee> getEmp();

    //添加员工
    public void addEmp(Employee employee);

    //编辑员工
    public void editEmp(Employee employee);

    //根据id删除员工
    public void deleteEmp(Employee employee);
}
