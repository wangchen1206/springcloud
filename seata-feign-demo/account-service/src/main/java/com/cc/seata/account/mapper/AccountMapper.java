package com.cc.seata.account.mapper;

import com.cc.seata.account.entity.Account;
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
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 扣除金额
     *
     * @param [userId, price]
     * @author wangchen
     * @createDate 2020/7/22
     **/
    @Update("update account set balance = balance - #{price} where id = #{userId} and balance >= #{price}")
    Integer reduceBalance(@Param("userId") Long userId, @Param("price") Integer price);


    /**
     * 获取账户余额
     *
     * @param [userId]
     * @author wangchen
     * @createDate 2020/7/22
     **/
    @Select("select balance from account where id = #{userId}")
    Integer getBalance(@Param("userId") Long userId);
}
