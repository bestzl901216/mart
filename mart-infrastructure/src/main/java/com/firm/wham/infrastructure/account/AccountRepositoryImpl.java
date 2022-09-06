package com.firm.wham.infrastructure.account;

import cn.hutool.core.convert.Convert;
import com.alibaba.cola.exception.BizException;
import com.firm.wham.domain.account.AccountEntity;
import com.firm.wham.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author ricardo zhou
 */
@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountGateway accountGateway;

    @Override
    public AccountEntity getBy(String name) {
        AccountDO accountDO = accountGateway.findBy(name);
        if (accountDO == null) {
            throw new BizException("账号信息不存在");
        }
        return Convert.convert(AccountEntity.class, accountDO);
    }
}
