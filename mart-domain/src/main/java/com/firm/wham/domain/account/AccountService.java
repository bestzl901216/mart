package com.firm.wham.domain.account;

import com.alibaba.cola.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ricardo zhou
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void signIn(String name, String password) {
        AccountEntity accountEntity = accountRepository.getBy(name);
        boolean pass = accountEntity.verifyPassword(password);
        if (!pass) {
            throw new BizException("密码错误");
        }
    }
}
