package com.firm.wham.infrastructure.account;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public Optional<AccountDO> findBy(String name) {
        Optional<AccountDO> optional = Optional.empty();
        if (StrUtil.isBlank(name)) {
            return optional;
        }
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getName, name);
        return Optional.ofNullable(accountMapper.selectOne(queryWrapper));
    }
}