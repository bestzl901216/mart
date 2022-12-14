package com.firm.wham.test;

import com.alibaba.cola.exception.BizException;
import com.firm.wham.domain.account.AccountService;
import com.firm.wham.starter.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @author ricardo zhou
 */
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class AccountServiceTest {
    @Resource
    private AccountService accountService;

    @Test
    public void testSignInSuccess() {
        Assertions.assertDoesNotThrow(() -> accountService.signIn("ricardo_zhou", "123456"));
    }

    @Test
    public void testErrorAccountName() {
        Assertions.assertThrows(BizException.class, () -> accountService.signIn("ricardo_zhou1", "123456"), "账号信息不存在");
    }

    @Test
    public void testErrorPassword() {
        Assertions.assertThrows(BizException.class, () -> accountService.signIn("ricardo_zhou", "1234567"), "密码错误");
    }

    @Test
    public void testSignUpSuccess() {
        String accountName = "test";
        String password = "123456";
        accountService.signUp(accountName, password);
        Assertions.assertDoesNotThrow(() -> accountService.signIn(accountName, password));
    }

    @Test
    public void testSignUpError() {
        String accountName = "ricardo_zhou";
        String password = "123456";
        Assertions.assertThrows(BizException.class, () -> accountService.signUp(accountName, password), "账号名重复");
    }
}
