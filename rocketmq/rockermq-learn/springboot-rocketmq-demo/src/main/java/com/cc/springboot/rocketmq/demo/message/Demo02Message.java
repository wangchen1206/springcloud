package com.cc.springboot.rocketmq.demo.message;

import lombok.Data;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/27
 */
@Data
public class Demo02Message {
    public static final String TOPIC = "TOPIC2";
    public static final String TAG = "tag2";

    /**
     * "topic:tag" 特定格式
     **/
    public static final String TOPIC_TAG = TOPIC+":"+TAG;

    private Integer id;
}
