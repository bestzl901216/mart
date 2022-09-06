package com.firm.wham.test;

import com.alibaba.cola.dto.Response;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import com.firm.wham.starter.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ricardo zhou
 */
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class AccountProviderTest {
    @Resource
    private AccountProvider accountProvider;

    @Test
    public void testSignInSuccess() {
        AccountSignInCmd signInCmd = new AccountSignInCmd();
        signInCmd.setName("ricardo zhou");
        signInCmd.setPassword("123456");
        Response response = accountProvider.signIn(signInCmd);
        assertThat(response.isSuccess()).isTrue();
    }
}
