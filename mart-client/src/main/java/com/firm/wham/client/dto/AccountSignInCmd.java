package com.firm.wham.client.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 登录指令
 *
 * @author ricardo zhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountSignInCmd extends Command {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}