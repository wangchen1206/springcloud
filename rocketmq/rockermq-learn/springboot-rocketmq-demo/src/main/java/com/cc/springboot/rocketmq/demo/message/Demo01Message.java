package com.cc.springboot.rocketmq.demo.message;

import lombok.Data;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/27
 */
@Data
public class Demo01Message {
    public static final String TOPIC = "DEMO_01";

    /**
     * 编号
     **/
    private Integer id;
}
