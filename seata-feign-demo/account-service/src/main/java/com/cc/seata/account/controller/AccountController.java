package com.cc.seata.account.controller;


import com.cc.seata.account.dto.AccountReduceBalanceDTO;
import com.cc.seata.account.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangchen
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/reduce-balance")
    public void reduceBalance(@RequestBody AccountReduceBalanceDTO accountReduceBalanceDTO) throws Exception {
        log.info("[reduceBalance] 收到减少余额请求，用户：{}，金额：{}",accountReduceBalanceDTO.getUserId(),accountReduceBalanceDTO.getPrice());
        accountService.reduceBanlance(accountReduceBalanceDTO.getUserId(),accountReduceBalanceDTO.getPrice());
    }

}
