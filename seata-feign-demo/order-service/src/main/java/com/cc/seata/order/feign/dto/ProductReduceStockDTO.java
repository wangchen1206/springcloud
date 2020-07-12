package com.cc.seata.order.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: ck
 * @Date: 2020/7/12 21:37
 */
@Data
@AllArgsConstructor
public class ProductReduceStockDTO {
    /** 商品编号 */
    private Long productId;
    /** 数量 */
    private Integer amount;
}
