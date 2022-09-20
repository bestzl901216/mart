package com.firm.wham.domain.security;

import com.firm.wham.domain.account.Account;
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

    public String authenticate(Account account) {
        Authentication authentication = AuthenticationFactory.instanceOf(account);
        authenticationRepository.add(authentication);
        return TokenUtil.generateToken(authentication);
    }
}
