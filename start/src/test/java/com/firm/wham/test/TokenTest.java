package com.firm.wham.test;

import com.alibaba.cola.exception.BizException;
import com.firm.wham.domain.account.AccountService;
import com.firm.wham.domain.account.TokenUtil;
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
public class TokenTest {

    @Resource
    private AccountService accountService;
    @Test
    public void testGenerateToken() {
        String token = accountService.signIn("ricardo_zhou", "123456");
        String accountName = TokenUtil.parseAccountName(token);
        Assertions.assertEquals("ricardo_zhou", accountName);

        String errorToken = token + "A";
        Assertions.assertThrows(BizException.class, () -> TokenUtil.parseAccountName(errorToken), "无效token");
    }
}
