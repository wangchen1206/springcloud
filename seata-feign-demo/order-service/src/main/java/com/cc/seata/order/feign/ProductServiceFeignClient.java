package com.cc.seata.order.feign;

import com.cc.seata.order.dto.ProductReduceStockDTO;
import com.cc.seata.order.feign.fallback.ProductServiceFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: ck
 * @Date: 2020/7/12 21:36
 */
//name是注册中心中服务的名字
@FeignClient(name="product-service",fallbackFactory = ProductServiceFeignClientFallbackFactory.class)
public interface ProductServiceFeignClient {

    @PostMapping("/product/reduce-stock")
    void reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO);
}
