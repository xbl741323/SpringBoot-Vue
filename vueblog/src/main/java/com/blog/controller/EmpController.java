package com.blog.controller;


import com.blog.bean.Employee;
import com.blog.bean.Result;
import com.blog.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class EmpController {

    //将Service注入web层
    @Autowired
    EmpService empService;

    Employee employee;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    //获取员工
    @RequestMapping("/api/getEmp")
    @ResponseBody
    public Collection<Employee> getEmp(){
        Collection<Employee> emps = empService.getEmp();
        System.out.println(emps);
        return emps;
    };

    //添加员工
    @RequestMapping("/api/addEmp")
    @ResponseBody
    public Result addEmp(@RequestBody Employee employee){
        System.out.println("输出添加的员工信息！");
        System.out.println(employee.toString());
        empService.addEmp(employee);
        Result result = new Result(1000,"添加成功！");
        return result;
    }

    //编辑员工
    @RequestMapping("/api/editEmp")
    @ResponseBody
    public Result editEmp(@RequestBody Employee employee){
        System.out.println("输出要编辑的员工信息！");
        System.out.println(employee);
        empService.editEmp(employee);
        Result result = new Result(1000,"编辑成功！");
        return result;
    }

    //删除员工
    @RequestMapping("/api/deleteEmp")
    @ResponseBody
    public Result deleteEmp(@RequestBody Employee employee){
        System.out.println("输出要删除的员工信息！");
        System.out.println(employee);
        empService.deleteEmp(employee);
        Result result = new Result(1000,"删除成功！");
        return  result;
    }

}
