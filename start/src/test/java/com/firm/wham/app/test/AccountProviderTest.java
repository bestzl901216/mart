package com.firm.wham.app.test;

import com.alibaba.cola.dto.Response;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ricardo zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountProviderTest {

    @Autowired
    private AccountProvider accountProvider;

    @Test
    public void testSignInSuccess() {
        AccountSignInCmd signInCmd = new AccountSignInCmd();
        Response response = accountProvider.signIn(signInCmd);
        assertThat(response.isSuccess()).isTrue();
    }
}
