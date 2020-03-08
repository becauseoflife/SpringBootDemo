-- --------------------
-  用于存储用户的记录
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