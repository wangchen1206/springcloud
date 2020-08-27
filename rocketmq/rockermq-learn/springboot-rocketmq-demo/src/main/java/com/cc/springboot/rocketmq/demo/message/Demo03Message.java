package com.cc.springboot.rocketmq.demo.message;

import lombok.Data;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/27
 */
@Data
public class Demo03Message {
    public static final String TOPIC = "TOPIC3";

    private Integer id;
}
