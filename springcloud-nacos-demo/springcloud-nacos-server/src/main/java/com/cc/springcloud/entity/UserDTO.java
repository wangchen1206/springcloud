package com.cc.springcloud.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <Description>
 *
 * @author wangchen
 * @createDate 2020/07/24
 */
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Integer id;
    private String username;
}
