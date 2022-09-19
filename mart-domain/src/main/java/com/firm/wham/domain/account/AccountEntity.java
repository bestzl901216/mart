package com.firm.wham.domain.account;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.cola.exception.BizException;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.EnumSet;

/**
 * @author ricardo zhou
 */
@Data
public class AccountEntity {

    private Integer id;
    private String name;
    private String encodedPassword;
    private EnumSet<AuthorityEnum> authorities;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
    private Integer version;

    public AccountEntity(String name, String password, String operator) {
        this.name = name;
        this.encodedPassword = encode(password);
        this.creator = operator;
        this.createTime = LocalDateTime.now();
        this.updater = creator;
        this.updateTime = createTime;
        this.version = 1;
    }

    public void verifyPassword(String password) {
        boolean pass = StrUtil.isNotBlank(password) && this.encodedPassword.equals(encode(password));
        if (!pass) {
            throw new BizException("密码错误");
        }
    }

    private static String encode(String password) {
        final String salt = "QWE_ASD_ZXC";
        return SecureUtil.sha256(salt + password);
    }

}
