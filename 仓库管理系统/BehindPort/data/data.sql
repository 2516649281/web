-- 仓库管理系统
create database WareHouseSystem;

use WareHouseSystem;

-- 账号表
create table user
(
    id            int primary key auto_increment comment '编号',
    user_Name     varchar(64) not null comment '用户名',
    user_passWord varchar(64) not null comment '用户密码',
    deleted       int         not null default 0 comment '冻结指数',
    create_Time   datetime    not null default CURRENT_TIMESTAMP comment '创建时间',
    update_Time   datetime    not null default CURRENT_TIMESTAMP comment '修改时间'
);

-- 管理员信息
create table administrator
(
    id            int primary key auto_increment comment '编号',
    admin_Name    varchar(64)  not null comment '管理者姓名',
    admin_Age     varchar(64)  not null comment '管理者年龄',
    admin_Sex     varchar(8)   not null comment '管理者性别',
    admin_Address varchar(128) not null comment '管理者住址',
    admin_Email   varchar(64)  not null comment '电子邮箱',
    admin_Phone   varchar(64)  not null comment '手机号',
    user_Id       int comment '用户编号',
    constraint user_administrator_fk foreign key (user_Id) references user (id)
);

-- 物资信息
create table material
(
    id                int primary key auto_increment comment '编号',
    material_Name     varchar(64)                        not null comment '物资名称',
    material_Category varchar(64)                        not null comment '物资类别',
    material_Price    int                                not null comment '物资价格',
    in_Time           datetime default CURRENT_TIMESTAMP not null comment '入库时间',
    out_Time          datetime default CURRENT_TIMESTAMP not null comment '出库时间',
    material_Address  varchar(64)                        not null comment '来源地址',
    material_Status   int                                not null default 0 comment '物资状态'
);