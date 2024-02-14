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
-- Table structure for shop_base
-- ----------------------------
DROP TABLE IF EXISTS `shop_base`;
CREATE TABLE `shop_base`
(
    `id`               varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `shop_name`        varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺名称',
    `shop_type`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '店铺类型',
    `contact_name`     varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '联系人',
    `mobile`           varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '手机号码',
    `email`            varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱号',
    `intro`            varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
    `logo`             varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'logo',
    `shop_location`    POINT                                                   NOT NULL COMMENT '店铺定位',
    `shop_address`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺地址',
    `business_hours`   varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业时间',
    `business_license` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '营业执照',
    `mt_qr`            varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '美团店铺小程序二维码',
    `elm_qr`           varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '饿了么店铺小程序二维码',
    `status`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '店铺状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of shop_base
-- ----------------------------
INSERT INTO `shop_base` (`id`, `shop_name`, `shop_type`, `contact_name`, `mobile`, `email`, `intro`, `logo`,
                         `shop_location`, `shop_address`, `business_hours`, `business_license`, `mt_qr`, `elm_qr`,
                         `status`)
VALUES ('af267b6e3ecb425682b5e7bf8bf9a6c1', '华莱士·拳击汉堡（比奇堡店）', '202002', '海绵宝宝', '12345678901',
        'mrhai@163.com', 'Welcome!', '', ST_GeomFromText('POINT(40.7128 -74.0060)'), '比奇堡', '{}',
        '/license/2024/02/af267b6e3ecb425682b5e7bf8bf9a6c1.jpg', '/qr/mt/2024/02/af267b6e3ecb425682b5e7bf8bf9a6c1.jpg',
        '', '201001'),
       ('9d8e4d714eab4da0a7dd0fb3ecfd04c7', '海霸王', '202004', '痞老板', '98765432109', 'pi@126.com', '', '',
        ST_GeomFromText('POINT(34.0522 -118.2437)'), '大洋海滩', '{}',
        '/license/2024/01/9d8e4d714eab4da0a7dd0fb3ecfd04c7.jpg', '', '', '201002');


-- ----------------------------
-- Table structure for shop_audit
-- ----------------------------
DROP TABLE IF EXISTS `shop_audit`;
CREATE TABLE `shop_audit`
(
    `id`           bigint                                                        NOT NULL AUTO_INCREMENT,
    `shop_id`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '店铺id',
    `audit_mind`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核意见',
    `audit_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '审核状态',
    `audit_people` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '审核人',
    `audit_date`   datetime                                                      NULL DEFAULT NULL COMMENT '审核时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_shop_id` (`shop_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of shop_audit
-- ----------------------------

INSERT INTO `shop_audit` (id, shop_id, audit_mind, audit_status, audit_people, audit_date)
VALUES (1, 'af267b6e3ecb425682b5e7bf8bf9a6c1', '营业执照图片模糊，请重新上传', '002001', '管理员a',
        '2024-02-05 20:42:06'),
       (2, 'af267b6e3ecb425682b5e7bf8bf9a6c1', '通过', '002003', '管理员a', '2024-02-06 23:32:25'),
       (3, '9d8e4d714eab4da0a7dd0fb3ecfd04c7', '卫生状况堪忧', '002001', '管理员a', '2024-02-06 23:35:09');


-- ----------------------------
-- Table structure for rebate_activity
-- ----------------------------

CREATE TABLE `rebate_activity`
(
    `id`                 bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '活动ID',
    `shop_id`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '关联店铺ID',
    `platform`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '所在平台，详见系统字典',
    `requirements`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参与要求，文本',
    `rebate_type`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '返利类型，详见系统字典',
    `rebate_details`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '返利细则，存储json',
    `limitation`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限制条件，详见系统字典',
    `limitation_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限制细则，存储json',
    `activity_type`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '活动类型，详见系统字典',
    `total_quota`        int                                                           NOT NULL COMMENT '总名额',
    `remaining_quota`    int                                                           NOT NULL COMMENT '剩余名额',
    `audit_date`         datetime                                                      NOT NULL COMMENT '开始时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rebate_activity
-- ----------------------------

INSERT INTO `rebate_activity` (id, shop_id, platform, requirements, rebate_type, rebate_details, limitation,
                               limitation_details, activity_type, total_quota, remaining_quota, audit_date)
VALUES (1, 'af267b6e3ecb425682b5e7bf8bf9a6c1', '301001', '用餐反馈（需含字含图）', '302001', '{}', '303002', '{}',
        '304001', 10, 10, '2024-01-26 20:32:25');

-- ----------------------------
-- Table structure for rebate_activity_history
-- ----------------------------

CREATE TABLE `rebate_activity_history`
(
    `id`                 bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '活动ID',
    `shop_id`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '关联店铺ID',
    `platform`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '所在平台，详见系统字典',
    `requirements`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参与要求，文本',
    `rebate_type`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '返利类型，详见系统字典',
    `rebate_details`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '返利细则，存储json',
    `limitation`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限制条件，详见系统字典',
    `limitation_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限制细则，存储json',
    `activity_type`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '活动类型，详见系统字典',
    `total_quota`        int                                                           NOT NULL COMMENT '总名额',
    `remaining_quota`    int                                                           NOT NULL COMMENT '剩余名额',
    `audit_date`         datetime                                                      NOT NULL COMMENT '开始时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rebate_activity_history
-- ----------------------------

INSERT INTO `rebate_activity_history` (id, shop_id, platform, requirements, rebate_type, rebate_details, limitation,
                                       limitation_details, activity_type, total_quota, remaining_quota, audit_date)
VALUES (1, 'af267b6e3ecb425682b5e7bf8bf9a6c1', '301001', '用餐反馈（需含字含图）', '302001', '{}', '303002', '{}',
        '304001', 10, 10, '2024-01-26 20:32:25');

-- ----------------------------
-- Table structure for mq_message
-- ----------------------------
DROP TABLE IF EXISTS `mq_message`;
CREATE TABLE `mq_message`
(
    `id`                 varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '消息id',
    `message_type`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '消息类型代码',
    `business_key1`      varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '关联业务信息',
    `business_key2`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '关联业务信息',
    `business_key3`      varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '关联业务信息',
    `mq_host`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '消息队列主机',
    `mq_port`            int                                                      NOT NULL COMMENT '消息队列端口',
    `mq_virtualhost`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '消息队列虚拟主机',
    `mq_queue`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '队列名称',
    `inform_num`         int UNSIGNED                                             NOT NULL COMMENT '通知次数',
    `state`              char(1) CHARACTER SET utf8 COLLATE utf8_general_ci       NOT NULL COMMENT '处理状态，0:初始，1:成功',
    `returnfailure_date` datetime                                                 NULL DEFAULT NULL COMMENT '回复失败时间',
    `returnsuccess_date` datetime                                                 NULL DEFAULT NULL COMMENT '回复成功时间',
    `returnfailure_msg`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复失败内容',
    `inform_date`        datetime                                                 NULL DEFAULT NULL COMMENT '最近通知时间',
    `stage_state1`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci       NULL DEFAULT NULL COMMENT '阶段1处理状态, 0:初始，1:成功',
    `stage_state2`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci       NULL DEFAULT NULL COMMENT '阶段2处理状态, 0:初始，1:成功',
    `stage_state3`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci       NULL DEFAULT NULL COMMENT '阶段3处理状态, 0:初始，1:成功',
    `stage_state4`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci       NULL DEFAULT NULL COMMENT '阶段4处理状态, 0:初始，1:成功',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mq_message
-- ----------------------------
INSERT INTO `mq_message`
VALUES ('f29a3149-7429-40be-8a4e-9909f32003b0', 'xc.mq.msgsync.coursepub', '111', NULL, NULL, '127.0.0.1', 5607, '/',
        'xc.course.publish.queue', 0, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for mq_message_history
-- ----------------------------
DROP TABLE IF EXISTS `mq_message_history`;
CREATE TABLE `mq_message_history`
(
    `id`                 varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '消息id',
    `message_type`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '消息类型代码',
    `business_key1`      varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '关联业务信息',
    `business_key2`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联业务信息',
    `business_key3`      varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联业务信息',
    `mq_host`            varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '消息队列主机',
    `mq_port`            int                                                     NOT NULL COMMENT '消息队列端口',
    `mq_virtualhost`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '消息队列虚拟主机',
    `mq_queue`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '队列名称',
    `inform_num`         int(10) UNSIGNED ZEROFILL                               NULL DEFAULT NULL COMMENT '通知次数',
    `state`              int(10) UNSIGNED ZEROFILL                               NULL DEFAULT NULL COMMENT '处理状态，0:初始，1:成功，2:失败',
    `returnfailure_date` datetime                                                NULL DEFAULT NULL COMMENT '回复失败时间',
    `returnsuccess_date` datetime                                                NULL DEFAULT NULL COMMENT '回复成功时间',
    `returnfailure_msg`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复失败内容',
    `inform_date`        datetime                                                NULL DEFAULT NULL COMMENT '最近通知时间',
    `stage_state1`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci      NULL DEFAULT NULL,
    `stage_state2`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci      NULL DEFAULT NULL,
    `stage_state3`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci      NULL DEFAULT NULL,
    `stage_state4`       char(1) CHARACTER SET utf8 COLLATE utf8_general_ci      NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mq_message_history
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;




