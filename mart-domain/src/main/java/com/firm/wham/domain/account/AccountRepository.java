package com.firm.wham.domain.account;

/**
 * @author ricardo zhou
 */
public interface AccountRepository {
    /**
     * 获取账号
     *
     * @param name 账号名称
     * @return 账号信息
     */
    AccountEntity getBy(String name);
}
