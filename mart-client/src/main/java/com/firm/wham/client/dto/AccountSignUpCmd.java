package com.firm.wham.client.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 账号注册命令
 * 
 * @author ricardo zhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountSignUpCmd extends Command {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
