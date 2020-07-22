package com.cc.seata.product.mapper;

import com.cc.seata.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangchen
 * @since 2020-07-22
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 获取库存
     *
     * @param [productId]
     * @author wangchen 
     * @createDate 2020/7/22
     **/
    @Select("select stock from product where id = #{productId}")
    Integer getStock(@Param("productId") Long productId);

    /**
     * 扣减库存
     *
     * @param [productId, amount]
     * @author wangchen 
     * @createDate 2020/7/22
     **/
    @Update("update product set stock = stock - #{amount} where id = #{productId} and stock >= #{amount}")
    Integer reductStock(@Param("productId") Long productId,@Param("amount") Integer amount);

}
