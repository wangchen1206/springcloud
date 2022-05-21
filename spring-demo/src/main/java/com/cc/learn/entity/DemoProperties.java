package com.cc.learn.entity;

import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/10/28
 */
@Component
public class DemoProperties {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
