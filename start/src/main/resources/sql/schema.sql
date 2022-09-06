DROP TABLE IF EXISTS `account`;
create table account
(
    id           int auto_increment comment 'id' primary key,
    name         varchar(64)   not null comment '账号名',
    md5_password varchar(256)  not null comment '密码（md5）',
    creator      varchar(64)   not null comment '创建者',
    create_time  datetime      not null comment '创建时间',
    updater      varchar(64)   not null comment '更新者',
    update_time  datetime      not null comment '更新时间',
    version      int default 0 null comment '时间戳',
    constraint account_name_unique unique (name)
);