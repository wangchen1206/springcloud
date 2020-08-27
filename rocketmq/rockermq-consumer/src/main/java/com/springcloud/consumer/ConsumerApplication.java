package com.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wachen
 */
@SpringBootApplication
@EnableBinding(ConsumerApplication.MySink.class)
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    public interface MySink{

        @Input("input1")
        SubscribableChannel input1();

        @Input("input2")
        SubscribableChannel input2();

        @Input("input3")
        SubscribableChannel input3();

        @Input("input4")
        SubscribableChannel input4();

        @Input("input5")
        PollableMessageSource input5();
    }

    public static class ConsumerCustomerRunner implements CommandLineRunner{

        @Autowired
        private MySink mySink;

        @Override
        public void run(String... args) throws Exception {
            while (true){
                mySink.input5().poll(m -> {
                    String payload = (String) m.getPayload();
                    System.out.println("pull msg: " + payload);
                }, new ParameterizedTypeReference<String>() {
                });
                Thread.sleep(2_000);
            }
        }
    }

}
