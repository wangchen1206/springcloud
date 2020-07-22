package com.cc.seata.account.service.impl;

import com.cc.seata.account.entity.Account;
import com.cc.seata.account.mapper.AccountMapper;
import com.cc.seata.account.service.IAccountService;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional //开启新事务  需要通过异常，回滚全局事务，不能catch
    public void reduceBanlance(Long userId, Integer price) throws Exception {
        log.info("[reduceBanlance] 当前XID: {}", RootContext.getXID());

        //<2> 检查余额
        checkBalance(userId,price);

        log.info("[reduceBanlance] 开始扣除用户 {} 余额",userId);
        //<3> 扣除余额
        Integer updateCount = accountMapper.reduceBalance(userId, price);
        if (updateCount == 0){
            log.warn("[reduceBanlance] 扣除用户 {} 余额失败",userId);
            throw new Exception("余额不足");
        }
        log.info("[reduceBalance] 扣除用户 {} 余额成功", userId);
    }

    private void checkBalance(Long userId,Integer price) throws Exception {
        log.info("[checkBalance] 检查用户 {} 余额",userId);
        Integer balance = accountMapper.getBalance(userId);
        if (balance < price){
            log.warn("[checkBalance] 用户 {} 余额不足，当前余额：{}",userId,balance);
            throw new Exception("余额不足");
        }
    }
}
