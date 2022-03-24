-- 校园跑腿
create database takeawaysystem;

use takeawaysystem;

-- 账号表
create table `account`
(
    account_id          int primary key auto_increment comment '用户编号',
    account_name        varchar(32) not null unique comment '用户名(账号)',
    account_password    varchar(32) not null comment '用户密码',
    account_deleted     varchar(1)  not null default 0 comment '冻结指数',
    account_create_time datetime    not null default CURRENT_TIMESTAMP comment '创建时间',
    account_update_time datetime    not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间'
);

-- 用户表
create table `user`
(
    user_id      int primary key auto_increment comment '用户编号',
    user_name    varchar(32) not null comment '用户姓名',
    user_age     int         not null comment '用户年龄',
    user_sex     varchar(4)  not null comment '用户性别',
    user_address varchar(64) not null comment '用户住址',
    user_phone   varchar(11) not null comment '手机号',
    user_duty    int         not null default 0 comment '用户职务(0 普通用户，1 接单员，2 跑腿员)',
    account_id   int comment '账号编号',
    constraint user_account_fk foreign key (account_id) references account (account_id)
);

-- 订单表
create table `order`
(
    order_id          int primary key auto_increment comment '订单编号',
    order_name        varchar(1024) not null comment '订单信息',
    user_id           int           not null comment '发起人编号',
    admin_id          int comment '接单员编号',
    run_id            int comment '跑腿员编号',
    order_create_time datetime      not null default CURRENT_TIMESTAMP comment '创建时间',
    order_update_time datetime      not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
    constraint user_order_fk foreign key (user_id) references user (user_id),
    constraint admin_order_fk foreign key (admin_id) references user (user_id),
    constraint run_account_fk foreign key (run_id) references user (user_id)
);
