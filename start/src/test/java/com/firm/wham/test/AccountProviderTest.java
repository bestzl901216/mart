package com.firm.wham.test;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.cola.dto.Response;
import com.firm.wham.client.api.AccountProvider;
import com.firm.wham.client.dto.AccountSignInCmd;
import com.firm.wham.infrastructure.account.AccountDo;
import com.firm.wham.infrastructure.account.AccountMapper;
import com.firm.wham.starter.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ricardo zhou
 */
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class AccountProviderTest {

    @Autowired
    private AccountProvider accountProvider;

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void testSignInSuccess() {
        AccountSignInCmd signInCmd = new AccountSignInCmd();
        Response response = accountProvider.signIn(signInCmd);
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    public void update() {
        AccountDo account = accountMapper.selectById(1L);
        assertThat(account.getMd5Password()).isEqualTo(SecureUtil.md5("123456"));
    }
}
