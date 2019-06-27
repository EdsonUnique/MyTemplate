-- 创建微信点餐数据库
create database wechat;
-- 创建商品表
create table product(
    id varchar(128) collate utf8_bin not null comment '主键id',
    name varchar(128) collate utf8_bin not null  comment '商品名称',
    price double not null comment '商品价格',
    stock int(64) not null comment '库存',
    description varchar(512) collate utf8_bin not null  comment '商品描述',
    icon varchar(128) collate utf8_bin not null  comment '商品图片链接地址',
    category_type int(10) collate utf8_bin not null  comment '商品所属类别',
    status int(2) not null comment '商品状态上架和下架',
    create_time timestamp not null default CURRENT_TIMESTAMP  comment '创建时间',
    update_time timestamp not null default CURRENT_TIMESTAMP  on UPDATE CURRENT_TIMESTAMP comment '修改时间',
    primary key(id)
)engine=InnoDB default charset =utf8;

-- 创建商品类别表
create table category(
    id int(64) unsigned not null auto_increment comment '主键id',
    name varchar(128) collate utf8_bin not null  comment '类别名称',
    type int not null  comment '类别类型',
    create_time timestamp not null default CURRENT_TIMESTAMP  comment '创建时间',
    update_time timestamp not null default CURRENT_TIMESTAMP  on UPDATE CURRENT_TIMESTAMP comment '修改时间',
    primary key(id)
)engine=InnoDB auto_increment=1 default charset =utf8;

-- 创建订单主表
create table order_master(
    order_id varchar(32) not null,
    buyer_name varchar(32) not null comment '买家姓名',
    buyer_phone varchar(32) not null comment '买家手机号',
    buyer_address varchar(128) not null comment '买家地址',
    buyer_openId varchar(64) not null comment '买家微信号id',
    order_amount decimal(10,2) not null comment '订单总金额',
    order_status tinyint(3) not null default 0 comment '订单状态，默认为新下单',
    pay_status tinyint(3) not null default 0 comment '支付状态，默认为未支付',
    create_time timestamp not null default CURRENT_TIMESTAMP  comment '创建时间',
    update_time timestamp not null default CURRENT_TIMESTAMP  on UPDATE CURRENT_TIMESTAMP comment '修改时间',
    primary key(order_id),
    key idx_buyer_openId (buyer_openId)
)ENGINE=InnoDB default charset=utf8;

-- 订单详情表
create table order_detail(
    detail_id varchar(32) not null,
    order_id varchar(32) not null,
    product_id varchar(128) not null comment '商品id',
    product_name varchar(128) not null  comment '商品名称',
    product_price double not null comment '商品价格',
    product_quantity int(11) not null comment '商品数量',
    product_icon varchar(128) not null comment '商品图片链接地址',
    create_time timestamp not null default CURRENT_TIMESTAMP  comment '创建时间',
    update_time timestamp not null default CURRENT_TIMESTAMP  on UPDATE CURRENT_TIMESTAMP comment '修改时间',
    primary key(detail_id),
    key idx_order_id (order_id),
)ENGINE=InnoDB default charset=utf8;

-- 用户表信息（卖家用户）
create table sell_info(

    seller_id varchar(32) not null,
    username varchar(32) not null,
    password varchar(32) not null,
    seller_openid varchar(64) not null,
    primary key(seller_id)
)


