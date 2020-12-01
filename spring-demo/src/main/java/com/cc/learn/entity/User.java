package com.cc.learn.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

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
