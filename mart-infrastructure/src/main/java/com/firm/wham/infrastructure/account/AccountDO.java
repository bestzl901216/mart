package com.firm.wham.infrastructure.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 账号
 *
 * @author ricardo zhou
 */
@Data
@TableName("account")
public class AccountDO {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String encodedPassword;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
    private Integer version;
}
