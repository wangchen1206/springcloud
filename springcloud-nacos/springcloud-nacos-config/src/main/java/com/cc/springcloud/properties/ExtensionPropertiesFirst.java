package com.cc.springcloud.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "ext1")
public class ExtensionPropertiesFirst {

    private String string1;
}
