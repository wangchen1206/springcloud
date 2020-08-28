package com.cc.springboot.rocketmq.demo.message;

import lombok.Data;

@Data
public class Demo04Message {

    public static final String TOPIC = "DEMO_04";

    /**
     * 编号
     */
    private Integer id;


}