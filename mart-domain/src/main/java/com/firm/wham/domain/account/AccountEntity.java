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
    private String md5Password;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
    private Integer version;
}
