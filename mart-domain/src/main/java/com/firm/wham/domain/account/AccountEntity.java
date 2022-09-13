package com.firm.wham.domain.account;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ricardo zhou
 */
@Data
public class AccountEntity {
    private Integer id;
    private String name;
    private String encodedPassword;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
    private Integer version;

    public AccountEntity(String name, String encodedPassword, String operator) {
        this.name = name;
        this.encodedPassword = encodedPassword;
        this.creator = operator;
        this.createTime = LocalDateTime.now();
        this.updater = creator;
        this.updateTime = createTime;
        this.version = 1;
    }
}
