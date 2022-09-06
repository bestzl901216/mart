package com.firm.wham.infrastructure.account;

import cn.hutool.core.util.StrUtil;
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
     * 查询账号信息
     *
     * @param name 账号名
     * @return 账号信息
     */
    public AccountDO findBy(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getName, name);
        return accountMapper.selectOne(queryWrapper);
    }
}