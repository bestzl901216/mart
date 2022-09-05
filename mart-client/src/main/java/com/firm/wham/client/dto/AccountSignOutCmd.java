package com.firm.wham.client.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账号登出指令
 * 
 * @author ricardo zhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountSignOutCmd extends Command {
}
