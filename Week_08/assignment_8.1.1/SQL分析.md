# 建表语句

```SQL
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL COMMENT '用户姓名',
  `mobile_no` varchar(45) NOT NULL COMMENT '手机号',
  `user_id` varchar(45) NOT NULL COMMENT '用户id，唯一编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB 

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(45) NOT NULL COMMENT '产品编号',
  `product_name` varchar(45) NOT NULL COMMENT '商品名称',
  `price` decimal(8,4) NOT NULL COMMENT '价格',
  `status` tinyint(4) NOT NULL COMMENT '商品状态',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB 

CREATE TABLE `product_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) NOT NULL COMMENT '订单编号',
  `user_id` varchar(45) NOT NULL COMMENT '下单用户id',
  `supplier_id` varchar(45) NOT NULL COMMENT '商户id',
  `status` int(11) NOT NULL COMMENT '订单状态',
  `product_num` int(11) NOT NULL COMMENT '商品数量',
  `product_amount_total` decimal(12,4) NOT NULL COMMENT '总价',
  `pay_amount_total` decimal(12,4) NOT NULL COMMENT '实际支付金额',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  `update_time` datetime NOT NULL COMMENT '订单更新时间',
  `pay_time` datetime NOT NULL COMMENT '订单结算时间',
  `product_id` varchar(45) NOT NULL COMMENT '商品编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `order_no_UNIQUE` (`order_no`)
) ENGINE=InnoDB 
```

# 分析
第六周的作业是一个非常简化的电商系统数据库sql。是否进行垂直拆分要从业务角度来看，即是否有不同的业务用到了同一张表的不同字段，如果业务耦合度比较低，是可以考虑进行垂直拆分的。但是本系统中由于比较简化，不需要垂直拆分。