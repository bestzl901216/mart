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
        AccountEntity accountEntity = accountRepository.getBy(name);
        accountEntity.verifyPassword(password);
        return authenticationService.authenticate(accountEntity);
    }

    public String signUp(String name, String password) {
        AccountEntity accountEntity = new AccountEntity(name, password, "ricardo_zhou");
        return authenticationService.authenticate(accountEntity);
    }
}