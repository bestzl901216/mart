package com.firm.wham.domain.account;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.firm.wham.domain.security.Authentication;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author ricardo zhou
 */
@Slf4j
@UtilityClass
public class TokenUtil {

    private static final AES AES = SecureUtil.aes();
    private static final String DELIMITER = "###";

    /**
     * 根据账号名称生成token
     */
    public String generateToken(Authentication authentication) {
        String accountName = authentication.getAccountName();
        String signInTimeStr = LocalDateTimeUtil.formatNormal(authentication.getSignInTime());
        List<String> list = List.of(accountName, signInTimeStr);
        return AES.encryptBase64(String.join(DELIMITER, list));
    }

    /**
     * 解析账号名称
     *
     * @param token 客户端传入的token
     */
    public String parseAccountName(String token) {
        Assert.isTrue(StrUtil.isNotBlank(token), "无效token");
        try {
            String[] list = AES.decryptStr(token).split(DELIMITER);
            return list[0];
        } catch (Exception e) {
            throw new BizException("无效token");
        }
    }
}
