/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : gc_system

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/02/2024
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id标识',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据字典名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据字典代码',
  `item_values` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '数据字典项--json格式\n  ',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tb_code_unique`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (12, '公共属性类型', '000', '[{\"code\":\"1\",\"codeInt\":1,\"desc\":\"使用态\"},{\"code\":\"0\",\"codeInt\":0,\"desc\":\"删除态\"},{\"code\":\"-1\",\"codeInt\":-1,\"desc\":\"暂时态\"}]');
INSERT INTO `dictionary` VALUES (13, '对象的审核状态', '002', '[{\"code\":\"002001\",\"desc\":\"审核未通过\"},{\"code\":\"002002\",\"desc\":\"未审核\"},{\"code\":\"002003\",\"desc\":\"审核通过\"}]');
INSERT INTO `dictionary` VALUES (14, '店铺状态', '201', '[{\"code\":\"201001\",\"desc\":\"正常营业\"},{\"code\":\"201002\",\"desc\":\"暂停营业\"}]');
INSERT INTO `dictionary` VALUES (15, '店铺类型', '202', '[{\"code\":\"202001\",\"desc\":\"包子粥铺\"},{\"code\":\"202002\",\"desc\":\"快餐简餐\"},{\"code\":\"202003\",\"desc\":\"甜品饮品\"},{\"code\":\"202004\",\"desc\":\"炸串小吃\"},{\"code\":\"202005\",\"desc\":\"火锅烧烤\"},{\"code\":\"202006\",\"desc\":\"汉堡西餐\"},{\"code\":\"202007\",\"desc\":\"零售\"}]');
INSERT INTO `dictionary` VALUES (16, '活动所在平台', '301', '[{\"code\":\"301001\",\"desc\":\"美团\"},{\"code\":\"301002\",\"desc\":\"饿了么\"}]');
INSERT INTO `dictionary` VALUES (17, '活动返利类型', '302', '[{\"code\":\"302001\",\"desc\":\"实付满减\"},{\"code\":\"302002\",\"desc\":\"按比例返现\"}]');
INSERT INTO `dictionary` VALUES (18, '活动限制条件', '303', '[{\"code\":\"303001\",\"desc\":\"无限制条件\"},{\"code\":\"303002\",\"desc\":\"n天内限抢一单\"}]');
INSERT INTO `dictionary` VALUES (19, '活动类型', '304', '[{\"code\":\"304001\",\"desc\":\"一般活动\"},{\"code\":\"304002\",\"desc\":\"大牌活动\"}]');

SET FOREIGN_KEY_CHECKS = 1;


select * from dictionary;