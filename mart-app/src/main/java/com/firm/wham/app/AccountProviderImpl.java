package com.firm.wham.app;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import com.firm.wham.client.dto.AccountSignOutCmd;
import com.firm.wham.client.dto.AccountSignUpCmd;
import com.firm.wham.domain.account.AccountEntity;
import com.firm.wham.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * @author ricardo zhou
 */
@Component
@CatchAndLog
@RequiredArgsConstructor
public class AccountProviderImpl implements AccountProvider {

    private final AccountRepository accountRepository;

    @Override
    public Response signUp(AccountSignUpCmd cmd) {
        return null;
    }

    @Override
    public Response signIn(AccountSignInCmd cmd) {
        AccountEntity accountEntity = accountRepository.getBy(cmd.getName());
        boolean pass = accountEntity.verifyPassword(cmd.getPassword());
        if (!pass) {
            throw new BizException("密码错误");
        }
        return Response.buildSuccess();
    }

    @Override
    public Response signOut(AccountSignOutCmd cmd) {
        return null;
    }
}