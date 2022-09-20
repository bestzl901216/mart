package com.firm.wham.domain.security;

import com.firm.wham.domain.account.Account;

import java.time.LocalDateTime;

/**
 * @author ricardo zhou
 */
public class AuthenticationFactory {

    public static Authentication instanceOf(Account account) {
        String accountName = account.getName();
        String accountEncodedPassword = account.getEncodedPassword();
        String[] accountAuthorities = account.authorities().stream().map(Account.authority::toString).toArray(String[]::new);
        LocalDateTime signInTime = LocalDateTime.now();
        return new Authentication(accountName, accountEncodedPassword, accountAuthorities, signInTime);
    }
}
