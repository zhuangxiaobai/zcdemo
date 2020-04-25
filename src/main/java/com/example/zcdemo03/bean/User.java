package com.example.zcdemo03.bean;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{
        @ApiModelProperty(value = "用户ID数据库自动生成，添加或者修改时不用加此字段")
        private Long id;
        @ApiModelProperty(value = "用户姓名String")
        private String name;
         @ApiModelProperty(value = "用户年龄String")
        private String age;
    }

