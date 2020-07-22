package com.cc.seata.product.controller;


import com.cc.seata.product.dto.ProductReduceStockDTO;
import com.cc.seata.product.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangchen
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/reduce-stock")
    public void reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO) throws Exception {
        log.info("[reduceStock] 收到减少库存请求，商品:{}，价格：{}",productReduceStockDTO.getProductId(),productReduceStockDTO.getAmount());

        productService.reduceStock(productReduceStockDTO.getProductId(),productReduceStockDTO.getAmount());
    }
    
}
