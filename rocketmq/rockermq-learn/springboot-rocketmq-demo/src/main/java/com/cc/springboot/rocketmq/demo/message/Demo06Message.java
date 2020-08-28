package com.cc.springboot.rocketmq.demo.message;

import lombok.Data;

@Data
public class Demo06Message {

    public static final String TOPIC = "DEMO_06";

    /**
     * 编号
     */
    private Integer id;


}