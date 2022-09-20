package com.firm.wham.infrastructure.account;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author ricardo zhou
 */
@Component
@RequiredArgsConstructor
public class AccountGateway {

    private final AccountMapper accountMapper;
    private final AccountAuthorityMapper accountAuthorityMapper;

    /**
     * 查询账号信息
     *
     * @param name 账号名
     * @return 账号信息
     */
    public Optional<AccountDO> findAccount(String name) {
        Optional<AccountDO> optional = Optional.empty();
        if (StrUtil.isBlank(name)) {
            return optional;
        }
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getName, name);
        return Optional.ofNullable(accountMapper.selectOne(queryWrapper));
    }

    public void addAccount(AccountDO accountDO) {
        try {
            accountMapper.insert(accountDO);
        } catch (DuplicateKeyException exception) {
            throw new BizException("账号名重复");
        }
    }

    public List<AccountAuthorityDO> accountAuthorities(String name) {
        if (StrUtil.isBlank(name)) {
            return List.of();
        }
        LambdaQueryWrapper<AccountAuthorityDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountAuthorityDO::getAccountName, name);
        return accountAuthorityMapper.selectList(queryWrapper);
    }

}