package com.cc.seata.order.service;

import com.cc.seata.order.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangchen
 * @since 2020-07-12
 */
public interface IOrdersService extends IService<Orders> {

    public List<Orders> getOrders(Orders orders);

    Integer createOrder(Long userId, Long productId, Integer price);
}
