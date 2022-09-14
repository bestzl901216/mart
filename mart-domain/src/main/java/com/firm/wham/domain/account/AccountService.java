package com.firm.wham.domain.account;

import com.alibaba.cola.exception.BizException;
import com.firm.wham.domain.security.Authentication;
import com.firm.wham.domain.security.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ricardo zhou
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthenticationRepository authenticationRepository;

    public String signIn(String name, String password) {
        AccountEntity accountEntity = accountRepository.getBy(name);
        boolean pass = passwordEncoder.matches(password, accountEntity.getEncodedPassword());
        if (!pass) {
            throw new BizException("密码错误");
        }
        Authentication authentication = new Authentication(accountEntity);
        authenticationRepository.add(authentication);
        return TokenUtil.generateToken(authentication);
    }
}