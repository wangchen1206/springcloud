package com.cc.seata.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 账户减少余额 DTO
 */
@Data
@AllArgsConstructor
public class AccountReduceBalanceDTO {

    /** 用户编号 */
    private Long userId;

    /** 扣减金额 */
    private Integer price;
    
}