package com.firm.wham.domain.account;

import com.firm.wham.domain.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ricardo zhou
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuthenticationService authenticationService;

    public String signIn(String name, String password) {
        Account account = accountRepository.getAccount(name);
        account.verifyPassword(password);
        return authenticationService.authenticate(account);
    }

    public String signUp(String name, String password) {
        Account account = new Account(name, password, "ricardo_zhou");
        accountRepository.addAccount(account);
        return authenticationService.authenticate(account);
    }
}