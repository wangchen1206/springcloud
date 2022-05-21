package com.cc.test.self.springboot.starter;

import com.cc.test.self.springboot.starter.config.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/20
 */
@SpringBootApplication
public class TestSelfSpringbootStarter {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestSelfSpringbootStarter.class, args);
        TestService bean = context.getBean(TestService.class);
        bean.hello();
        HashMap<String, String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        System.out.println(map.size());
    }
}
