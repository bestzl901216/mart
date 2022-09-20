DROP TABLE IF EXISTS `account`;
create table account
(
    id               int auto_increment comment 'id' primary key,
    name             varchar(64)  not null comment '账号名',
    encoded_password varchar(256) not null comment '编码后的密码',
    creator          varchar(64)  not null comment '创建者',
    create_time      datetime     not null default current_time comment '创建时间',
    updater          varchar(64)  not null comment '更新者',
    update_time      datetime     not null default current_time on update current_time comment '更新时间',
    version          int          not null default 0 comment '版本号',
    constraint uk_name unique (name)
);

DROP TABLE IF EXISTS `account_authority`;
create table account_authority
(
    id           int auto_increment comment 'id' primary key,
    account_name varchar(64) not null comment '账号名(关联 account.name)',
    type         varchar(64) not null comment '类型(限制权限的使用范围)',
    role         varchar(64) not null COMMENT '角色',
    creator      varchar(64) not null comment '创建者',
    create_time  datetime    not null default current_time comment '创建时间',
    updater      varchar(64) not null comment '更新者',
    update_time  datetime    not null default current_time on update current_time comment '更新时间',
    constraint uk_accountName_type_role unique (account_name, type, role)
);