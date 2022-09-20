package com.firm.wham.infrastructure.account;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ricardo zhou
 */
@Data
@TableName("account_authority")
public class AccountAuthorityDO {
    private Integer id;
    private String accountName;
    private String type;
    private String role;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
}
