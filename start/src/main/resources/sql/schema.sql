DROP TABLE IF EXISTS `account`;
create table account
(
    id           bigint auto_increment comment 'id' primary key,
    name         varchar(64)      not null comment '账号名',
    md5_password varchar(256)     not null comment '密码（md5）',
    create_time  datetime         not null comment '创建时间',
    update_time  datetime         not null comment '更新时间',
    timestamp    bigint default 0 null comment '时间戳',
    constraint account_name_unique unique (name)
);