package com.cc.seata.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ck
 * @Date: 2020/7/12 20:34
 */
@SpringBootApplication
@MapperScan("com.cc.seata.product.mapper")
public class ProductsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class,args);
    }
}
