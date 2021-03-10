package com.blog.serviceImpl;

import com.blog.bean.EmpFilters;
import com.blog.bean.Employee;
import com.blog.mapper.EmpMapper;
import com.blog.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;

    @Override
    public Collection<Employee> getEmp(EmpFilters empFilters) {

        return empMapper.getEmp(empFilters);
    }

    @Override
    public Integer getEmpCount() {

        return empMapper.getEmpCount();
    }

    @Override
    public void addEmp(Employee employee) {

        empMapper.addEmp(employee);
    }

    @Override
    public void editEmp(Employee employee) {

        empMapper.editEmp(employee);
    }

    @Override
    public void deleteEmp(Employee employee) {

        empMapper.deleteEmp(employee);

    }

}
