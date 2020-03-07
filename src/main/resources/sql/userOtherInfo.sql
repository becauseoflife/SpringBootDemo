-- --------------------
-  用于存储用户的记录
-- --------------------

CREATE TABLE ${tableName} (
  `id` VARCHAR(30) NOT NULL COMMENT 'id',
  `date` DATETIME NOT NULL COMMENT '时间',
  `cost` VARCHAR(5) NOT NULL COMMENT '花费',
  `type` VARCHAR(5) NOT NULL COMMENT '种类'
);