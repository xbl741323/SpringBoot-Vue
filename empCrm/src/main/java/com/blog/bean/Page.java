package com.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//page分页类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer current;
    private Integer size;

}
