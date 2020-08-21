package com.cc.springcloud.properties;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
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
//@NacosConfigurationProperties(prefix = "order", dataId = "${nacos.config.data-id}", type = ConfigType.YAML)
public class OrderProperties implements Serializable {

    private Integer payTimeoutSeconds;

    private Integer createFrequencySeconds;
}
