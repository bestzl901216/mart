package com.firm.wham.infrastructure.account;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author ricardo zhou
 */
@Component
@RequiredArgsConstructor
public class AccountGateway {

    private final AccountMapper accountMapper;

    /**
     * 根据账号名称查询账号信息，未找到时抛出异常
     *
     * @param name 账号名
     * @return 账号信息
     */
    AccountDo get(String name) {
        LambdaQueryWrapper<AccountDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDo::getName, name);
        // todo 抛出異常
        return accountMapper.selectOne(queryWrapper);
    }
}