package com.firm.wham.app;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import com.firm.wham.client.dto.AccountSignOutCmd;
import com.firm.wham.client.dto.AccountSignUpCmd;
import com.firm.wham.domain.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * @author ricardo zhou
 */
@Component
@CatchAndLog
@RequiredArgsConstructor
public class AccountProviderImpl implements AccountProvider {

    private final AccountService accountService;

    @Override
    public Response signUp(AccountSignUpCmd cmd) {
        throw new BizException("功能未实现");
    }

    @Override
    public SingleResponse<String> signIn(AccountSignInCmd cmd) {
        String token = accountService.signIn(cmd.getName(), cmd.getPassword());
        return SingleResponse.of(token);
    }

    @Override
    public Response signOut(AccountSignOutCmd cmd) {
        throw new BizException("功能未实现");
    }
}