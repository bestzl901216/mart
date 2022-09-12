package com.firm.wham.test;

import com.alibaba.cola.exception.BizException;
import com.firm.wham.starter.Application;
import com.firm.wham.starter.config.security.JwtTokenGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author ricardo zhou
 */
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class JwtTokenTest {

    @Test
    public void testGenerateToken() {
        String token = JwtTokenGenerator.generateToken("ricardo_zhou");
        String accountName = JwtTokenGenerator.parseAccountName(token);
        Assertions.assertEquals("ricardo_zhou", accountName);

        String errorToken = token + "A";
        Assertions.assertThrows(BizException.class, () -> JwtTokenGenerator.parseAccountName(errorToken), "无效token");
    }
}
