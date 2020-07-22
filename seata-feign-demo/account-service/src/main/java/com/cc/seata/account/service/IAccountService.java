package com.cc.seata.account.service;

import com.cc.seata.account.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangchen
 * @since 2020-07-22
 */
public interface IAccountService extends IService<Account> {

    /**
     * 扣除金额
     *
     * @param [userId, price]
     * @author wangchen
     * @createDate 2020/7/22
     **/
    void reduceBanlance(Long userId,Integer price) throws Exception;
}
