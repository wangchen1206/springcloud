package com.cc.springcloud.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <Description>
 *
 * @author wangchen
 * @createDate 2020/07/24
 */
@Data
@Builder
public class User {

    private Integer id;
    private String username;
}
