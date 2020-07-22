package com.cc.seata.product.service;

import com.cc.seata.product.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangchen
 * @since 2020-07-22
 */
public interface IProductService extends IService<Product> {

    /**
     * 扣减库存
     *
     * @param [productId, amount]
     * @author wangchen
     * @createDate 2020/7/22
     **/
    void reduceStock(Long productId,Integer amount) throws Exception;

}
