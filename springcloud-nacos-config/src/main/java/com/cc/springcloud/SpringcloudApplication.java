package com.cc.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author cc
 */
@SpringBootApplication
@Slf4j
public class SpringcloudApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringcloudApplication.class, args);
//        while (true) {
//            String username = applicationContext.getEnvironment().getProperty("user.name");
//            String age = applicationContext.getEnvironment().getProperty("user.age");
//            log.info("the username is :" + username + ", age is :" + age);
//            TimeUnit.SECONDS.sleep(3);
//        }
    }


}
