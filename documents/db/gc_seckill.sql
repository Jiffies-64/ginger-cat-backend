/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : gc_content

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/02/2024
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rebate_activity_seckill
-- ----------------------------
DROP TABLE IF EXISTS `rebate_activity_seckill`;
CREATE TABLE `rebate_activity_seckill`
(
    `id`              bigint                                                       NOT NULL COMMENT '活动ID',
    `shop_id`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联店铺ID',
    `total_quota`     int                                                          NOT NULL COMMENT '总名额',
    `remaining_quota` int                                                          NOT NULL COMMENT '剩余名额',
    `start_time`      datetime                                                     NOT NULL COMMENT '开始时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rebate_activity
-- ----------------------------

INSERT INTO `rebate_activity_seckill` (id, shop_id, total_quota, remaining_quota, start_time)
VALUES (1, 'af267b6e3ecb425682b5e7bf8bf9a6c1', 10, 10, '2024-02-28 20:32:25');
