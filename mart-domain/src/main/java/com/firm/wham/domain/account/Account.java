package com.firm.wham.domain.account;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.cola.exception.BizException;
import lombok.Getter;

import java.util.List;

/**
 * @author ricardo zhou
 */
@Getter
public class Account {
    private String name;
    private String encodedPassword;
    private String operator;
    private Integer version;

    Account(String name, String password, String operator) {
        // todo 添加账户名、密码的限制规则
        this.name = name;
        this.encodedPassword = encode(password);
        this.operator = operator;
        this.version = 1;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void verifyPassword(String password) {
        boolean pass = StrUtil.isNotBlank(password) && this.encodedPassword.equals(encode(password));
        if (!pass) {
            throw new BizException("密码错误");
        }
    }

    public List<authority> authorities() {
        AccountRepository accountRepository = SpringUtil.getBean(AccountRepository.class);
        return accountRepository.accountAuthorities(this.name);
    }

    private static String encode(String password) {
        final String salt = "QWE_ASD_ZXC";
        return SecureUtil.sha256(salt + password);
    }

    public record authority(String type, String role) {
        @Override
        public String toString() {
            return String.join("_", this.type, this.role);
        }
    }

    private Account() {
    }

    public static class Builder {

        private Builder() {

        }

        private Account account = new Account();

        public Builder name(String name) {
            account.name = name;
            return this;
        }

        public Builder encodedPassword(String encodedPassword) {
            account.encodedPassword = encodedPassword;
            return this;
        }

        public Builder operator(String operator) {
            account.operator = operator;
            return this;
        }

        public Builder version(Integer version) {
            account.version = version;
            return this;
        }

        public Account build() {

            return account;
        }
    }
}
