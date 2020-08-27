package com.example.springcloudstream.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private Date createTime = new Date();
    private String name;
    
}
