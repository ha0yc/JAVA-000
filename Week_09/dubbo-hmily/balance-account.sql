CREATE TABLE `balance_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `rmb_balance` decimal(10,2) NOT NULL,
  `usd_balance` decimal(10,2) NOT NULL,
  `freezed_rmb_balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  `freezed_usd_balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `balance_account_user_id_uindex` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4