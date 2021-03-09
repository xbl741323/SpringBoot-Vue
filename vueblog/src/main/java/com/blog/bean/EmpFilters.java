package com.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//员工筛选条件类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpFilters {
    private Integer current;
    private Integer size;
    private String empName;
    private Integer deptId;

    public Integer getCurrent() {
        return (current-1)*size;
    }

}
