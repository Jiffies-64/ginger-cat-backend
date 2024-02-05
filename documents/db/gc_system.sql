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

SET FOREIGN_KEY_CHECKS = 1;


select * from dictionary;