package com.firm.wham.domain.account;

import java.util.List;

/**
 * @author ricardo zhou
 */
public interface AccountRepository {
    /**
     * 获取账号
     *
     * @param name 账号名
     * @return 账号信息
     */
    Account getAccount(String name);

    /**
     * 保存账号
     *
     * @param account 账号
     */
    void addAccount(Account account);

    /**
     * 获取账号权限
     *
     * @param name 账号名
     * @return 权限列表
     */
    List<Account.authority> accountAuthorities(String name);
}
