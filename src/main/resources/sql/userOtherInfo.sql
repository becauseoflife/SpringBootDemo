-- --------------------
-  用于存储用户的信息
-- --------------------

CREATE TABLE `user_info` (
  `id` varchar(20) NOT NULL,
  `account` char(10) NOT NULL,
  `password` char(16) NOT NULL,
  `name` varchar(5) NOT NULL,
  `netName` char(10) NOT NULL,
  `telephone` char(11) NOT NULL,
  `hope_save` double DEFAULT '0',
  `week_max_cost` double DEFAULT '0',
  `month_max_cost` double DEFAULT '0',
  PRIMARY KEY (`account`,`id`)
);


-- --------------------
-  用于存储用户的记账记录
-- --------------------
CREATE TABLE ${tableName} (
  `id` CHAR(20) NOT NULL COMMENT 'id',
  `year` CHAR(4) NOT NULL COMMENT '年份',
  `month` CHAR(2) NOT NULL COMMENT '月份',
  `day` CHAR(2) NOT NULL COMMENT '日',
  `date` DATETIME NOT NULL COMMENT '日期时间',
  `cost` CHAR(10) NOT NULL COMMENT '花费',
  `type` CHAR(5) NOT NULL COMMENT '种类'
); 