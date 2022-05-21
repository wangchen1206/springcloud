package com.cc.server.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/20
 */
@ConfigurationProperties(prefix = "cc.server")
public class CCServerProperties {

    private static final Integer DEFAULT_PORT = 8000;

    private Integer port = DEFAULT_PORT;

    public static Integer getDefaultPort(){
        return DEFAULT_PORT;
    }

    public Integer getPort(){
        return this.port;
    }

    public CCServerProperties setPort(Integer port){
        this.port = port;
        return this;
    }
}
