package com.cc.seata.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.seata.order.entity.Orders;
import com.cc.seata.order.feign.AccountServiceFeignClient;
import com.cc.seata.order.feign.ProductServiceFeignClient;
import com.cc.seata.order.dto.AccountReduceBalanceDTO;
import com.cc.seata.order.dto.ProductReduceStockDTO;
import com.cc.seata.order.mapper.OrdersMapper;
import com.cc.seata.order.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangchen
 * @since 2020-07-12
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;

    @Autowired
    private AccountServiceFeignClient accountServiceFeignClient;


    @Override
    public List<Orders> getOrders(Orders orders) {
        return this.list(new QueryWrapper<Orders>().lambda()
                    .eq(orders.getProductId()!=null,Orders::getProductId,orders.getProductId()));
    }

    @Override
    @GlobalTransactional //声明全局事务
    public Integer createOrder(Long userId, Long productId, Integer price) {
        Integer amount = 1; //购买数量，暂时设置为1
        log.info("[createOrder] 当前XID: {}", RootContext.getXID());

        //扣减库存
        productServiceFeignClient.reduceStock(new ProductReduceStockDTO(productId,amount));

        //扣减金额
        accountServiceFeignClient.reduceBalance(new AccountReduceBalanceDTO(productId,price));

        //保存订单
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setProductId(productId);
        orders.setPayAmount(amount*price);
        this.ordersMapper.saveOrder(orders);
        log.info("[createOrder] 保存订单：{}",orders.getId());

        return orders.getId();
    }


}
