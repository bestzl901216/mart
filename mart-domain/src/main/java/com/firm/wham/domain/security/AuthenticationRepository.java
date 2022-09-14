package com.firm.wham.domain.security;

import java.util.Optional;

/**
 * @author ricardo zhou
 */
public interface AuthenticationRepository {

    /**
     * 保存登录信息
     *
     * @param authentication 登录信息
     */
    void add(Authentication authentication);

    /**
     * 登录信息
     *
     * @param accountName 账号名称
     * @return 登录信息
     */
    Optional<Authentication> find(String accountName);
}
