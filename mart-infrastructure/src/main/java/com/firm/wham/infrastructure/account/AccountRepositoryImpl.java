package com.firm.wham.infrastructure.account;

import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.firm.wham.domain.account.Account;
import com.firm.wham.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ricardo zhou
 */
@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountGateway accountGateway;

    @Override
    public Account getAccount(String name) {
        Optional<AccountDO> optional = accountGateway.findAccount(name);
        AccountDO accountDO = optional.orElseThrow(() -> new BizException("账号信息不存在"));
        return Convert.INSTANCE.convert(accountDO);
    }

    @Override
    public void addAccount(Account account) {
        Assert.notNull(account, "账号信息不存在");
        AccountDO accountDO = Convert.INSTANCE.convert(account);
        accountGateway.addAccount(accountDO);
    }

    @Override
    public List<Account.authority> accountAuthorities(String name) {
        List<AccountAuthorityDO> authorities = accountGateway.accountAuthorities(name);
        return Convert.INSTANCE.convert(authorities);
    }
}
