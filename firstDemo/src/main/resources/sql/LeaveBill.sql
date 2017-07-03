-- auto Generated on 2017-07-03 14:36:22 
-- DROP TABLE IF EXISTS `leave_bill`; 
CREATE TABLE `leave_bill`(
    `manager_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'managerName',
    `id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `days` INT (11) NOT NULL DEFAULT -1 COMMENT 'days',
    `content` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'content',
    `create_at` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createAt',
    `remark` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'remark',
    `user_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'userName',
    `state` INT (11) NOT NULL DEFAULT -1 COMMENT 'state',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`leave_bill`';
