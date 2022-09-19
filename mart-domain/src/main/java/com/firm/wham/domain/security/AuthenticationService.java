package com.firm.wham.domain.security;

import com.firm.wham.domain.account.AccountEntity;
import com.firm.wham.domain.account.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ricardo zhou
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    public String authenticate(AccountEntity account) {
        Authentication authentication = new Authentication(account);
        authenticationRepository.add(authentication);
        return TokenUtil.generateToken(authentication);
    }
}
