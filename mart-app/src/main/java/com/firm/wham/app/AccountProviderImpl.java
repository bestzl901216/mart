package com.firm.wham.app;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import com.firm.wham.client.dto.AccountSignOutCmd;
import com.firm.wham.client.dto.AccountSignUpCmd;
import org.springframework.stereotype.Component;


/**
 * @author ricardo zhou
 */
@Component
@CatchAndLog
public class AccountProviderImpl implements AccountProvider {

    @Override
    public Response signUp(AccountSignUpCmd cmd) {
        return null;
    }

    @Override
    public Response signIn(AccountSignInCmd cmd) {
        return null;
    }

    @Override
    public Response signOut(AccountSignOutCmd cmd) {
        return null;
    }
}