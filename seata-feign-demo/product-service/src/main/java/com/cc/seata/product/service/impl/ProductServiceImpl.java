package com.cc.seata.product.service.impl;

import com.cc.seata.product.entity.Product;
import com.cc.seata.product.mapper.ProductMapper;
import com.cc.seata.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangchen
 * @since 2020-07-22
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional   //<1> 开启新事务
    public void reduceStock(Long productId, Integer amount) throws Exception {
        log.info("[reduceStock] 当前XID是："+ RootContext.getXID());

        //<2> 检查库存
        checkStock(productId,amount);
        //<3> 扣减库存
        Integer updateStock = productMapper.reductStock(productId, amount);
        //扣除失败，通过异常回滚全局事务
        if (updateStock == 0){
            log.warn("[reduceStock] 扣除 {} 库存失败",productId);
            throw new Exception("库存不足");
        }

        //扣除成功
        log.info("[reduceStock] 扣除 {} 库存成功",productId);

    }

    private void checkStock(Long productId,Integer amount) throws Exception {
        log.info("[checkStock] 检查 {} 库存",productId);
        Integer stock = productMapper.getStock(productId);
        if (stock < amount){
            log.warn("[checkStock] {} 库存不足，当前库存：{}",productId,stock);
            throw new Exception("库存不足");
        }
    }
}
