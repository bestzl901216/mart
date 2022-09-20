package com.firm.wham.domain.security;

import java.time.LocalDateTime;

/**
 * 登录信息（值对象）
 *
 * @author ricardo zhou
 */
public record Authentication(String accountName, String accountEncodedPassword, String[] accountAuthorities,
                             LocalDateTime signInTime) {
}
