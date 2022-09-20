package com.firm.wham.infrastructure.account;

import com.firm.wham.domain.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ricardo zhou
 */
@Mapper
public interface Convert {
    Convert INSTANCE = Mappers.getMapper(Convert.class);

    /**
     * 账号（领域对象转数据库映射对象）
     */
    @Mapping(target = "creator", source = "operator")
    @Mapping(target = "updater", source = "operator")
    AccountDO convert(Account account);

    /**
     * 账号（数据库映射对象转领域对象）
     */
    @Mapping(target = "operator", source = "updater")
    Account convert(AccountDO accountDO);

    /**
     * 权限（领域对象转数据库映射对象）
     */
    AccountAuthorityDO convert(Account.authority authority);

    /**
     * 权限（数据库映射对象转领域对象）
     */
    Account.authority convert(AccountAuthorityDO authority);

    /**
     * 权限（数据库映射对象转领域对象）
     */
    List<Account.authority> convert(List<AccountAuthorityDO> authorities);
}
