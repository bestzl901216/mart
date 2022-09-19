package com.firm.wham.infrastructure.account;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.ArrayTypeHandler;

import java.time.LocalDateTime;

/**
 * 账号
 *
 * @author ricardo zhou
 */
@Data
@TableName("account")
public class AccountDO {
    private Integer id;
    private String name;
    private String encodedPassword;
    @TableField(typeHandler = ArrayTypeHandler.class)
    private String[] authorities;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
    private Integer version;
}
