CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL COMMENT '用户姓名',
  `mobile_no` varchar(45) NOT NULL COMMENT '手机号',
  `user_id` varchar(45) NOT NULL COMMENT '用户id，唯一编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_no_UNIQUE` (`mobile_no`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1

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
) ENGINE=InnoDB 