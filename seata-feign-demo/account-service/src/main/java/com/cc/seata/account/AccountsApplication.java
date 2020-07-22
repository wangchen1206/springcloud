package com.cc.seata.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ck
 * @Date: 2020/7/12 20:34
 */
@SpringBootApplication
@MapperScan("com.cc.seata.account.mapper")
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class,args);
    }
}
