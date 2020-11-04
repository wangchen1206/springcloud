package com.cc.spring.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cc
 * @since 2020-07-11
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String userInfor;

    private Date createDate;



}
