package com.cc.seata.order.mapper;

import com.cc.seata.order.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangchen
 * @since 2020-07-12
 */
public interface OrdersMapper extends BaseMapper<Orders> {


    @Insert("insert into orders (user_id,product_id,pay_amount) values(#{userId},#{productId},#{payAmount})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int saveOrder(Orders orders);

}
