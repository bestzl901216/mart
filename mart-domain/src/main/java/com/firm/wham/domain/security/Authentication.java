package com.firm.wham.domain.security;

import com.firm.wham.domain.account.AccountEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 登录信息（值对象）
 *
 * @author ricardo zhou
 */
@Getter
public class Authentication {
    private final String accountName;
    private final String encodedPassword;
    private final Set<String> authorities;
    private final LocalDateTime signInTime;

    public Authentication(AccountEntity account) {
        this.accountName = account.getName();
        this.encodedPassword = account.getEncodedPassword();
        this.authorities = Set.of("TEST");
        this.signInTime = LocalDateTime.now();
    }
}
