package com.firm.wham.domain.account;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ricardo zhou
 */
@Data
public class AccountEntity {
    private Integer id;
    private String name;
    private String md5Password;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
    private Integer version;

    public AccountEntity(String name, String password, String operator) {
        this.name = name;
        this.md5Password = SecureUtil.md5(password);
        this.creator = operator;
        this.createTime = LocalDateTime.now();
        this.updater = creator;
        this.updateTime = createTime;
        this.version = 1;
    }

    public boolean verifyPassword(String password) {
        return StrUtil.isNotBlank(password) && this.md5Password.equals(SecureUtil.md5(password));
    }
}
