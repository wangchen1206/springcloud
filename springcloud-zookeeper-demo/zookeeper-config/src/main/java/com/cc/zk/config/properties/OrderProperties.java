package com.cc.zk.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "order")
public class OrderProperties implements Serializable {

    private Integer payTimeoutSeconds;

    private Integer createFrequencySeconds;
}
