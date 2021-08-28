SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE `game`
(
    `id`           int(20)         NOT NULL ,
    `name`         varchar(25)    NOT NULL COMMENT '游戏名',
    `price`        decimal(10, 2) NULL DEFAULT NULL COMMENT '游戏价格',
    `publish_date` date           NULL DEFAULT NULL COMMENT '发售日期',
    `score`        double         NULL DEFAULT NULL COMMENT '游戏评分',
    `image`        varchar(255)   NULL DEFAULT NULL COMMENT '图片地址',
    `type`         varchar(255)   NULL DEFAULT NULL COMMENT '游戏类型 rpg action shooter ',
    `status`       tinyint(1)     NOT NULL COMMENT '状态 1 - 在售 9 - 删除 0 - 未发售'
) ENGINE = InnoDB
  ROW_FORMAT = Dynamic;