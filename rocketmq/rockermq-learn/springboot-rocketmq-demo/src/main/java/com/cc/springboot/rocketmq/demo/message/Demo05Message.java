package com.cc.springboot.rocketmq.demo.message;

import lombok.Data;

@Data
public class Demo05Message {

    public static final String TOPIC = "DEMO_05";

    /**
     * 编号
     */
    private Integer id;


}