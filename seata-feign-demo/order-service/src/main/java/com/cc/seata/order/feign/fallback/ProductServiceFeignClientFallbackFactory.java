package com.cc.seata.order.feign.fallback;

import com.cc.seata.order.feign.ProductServiceFeignClient;
import com.cc.seata.order.dto.ProductReduceStockDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <Description>
 *
 * @author wangchen
 * @createDate 2020/07/22
 */
@Component
@Slf4j
public class ProductServiceFeignClientFallbackFactory implements FallbackFactory<ProductServiceFeignClient> {
    @Override
    public ProductServiceFeignClient create(Throwable throwable) {
        return new ProductServiceFeignClient() {
            @Override
            public void reduceStock(ProductReduceStockDTO productReduceStockDTO) {
                log.error("系统发生异常！，进行服务降级");
            }
        };
    }
}
