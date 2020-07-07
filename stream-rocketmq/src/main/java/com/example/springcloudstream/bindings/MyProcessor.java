package com.example.springcloudstream.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @Author: ck
 * @Date: 2020/7/3 12:21
 */
public interface MyProcessor {
    String INPUT = "input";
    String OUTPUT ="output";
    String INPUT1 = "input1";
    String OUTPUT1 ="output1";
    String INPUT2 = "input2";
    //String OUTPUT2 ="output2";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT1)
    SubscribableChannel input1();

    @Output(OUTPUT1)
    MessageChannel output1();

    @Input(INPUT2)
    SubscribableChannel input2();

    //@Output(OUTPUT2)
    //MessageChannel output2();
}
