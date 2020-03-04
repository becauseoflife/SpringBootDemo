-- --------------------
-  用于存储用户的记录
-- --------------------

CREATE TABLE ${tableName} (
  `id` CHAR(15) NOT NULL COMMENT '账号',
  `date` DATE NOT NULL COMMENT '时间',
  `cost` DOUBLE NOT NULL COMMENT '花费',
  `type` VARCHAR(5) NOT NULL COMMENT '种类',
  PRIMARY KEY (`id`));