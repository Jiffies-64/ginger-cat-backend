/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : gc_media

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/02/2024
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for media_files
-- ----------------------------
DROP TABLE IF EXISTS `media_files`;
CREATE TABLE `media_files`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件id,md5值',
  `company_id` bigint NULL DEFAULT NULL COMMENT '商户ID',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户名称',
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `file_type` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型（图片、文档，视频）',
  `tags` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `bucket` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储目录',
  `file_path` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `file_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件id',
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件访问地址',
  `username` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传人',
  `create_date` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `change_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态,1:正常，0:不展示',
  `remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_status` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `audit_mind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_fileid`(`file_id`) USING BTREE COMMENT '文件id唯一索引 '
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '媒资信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of media_files
-- ----------------------------
INSERT INTO `media_files` VALUES ('1580180577525002241', 1232141425, NULL, '1.jpg', '001003', NULL, 'mediafiles', NULL, '8383a8c2c1d098fcc07da7d6e79ae31e', '/mediafiles/2022/10/12/8383a8c2c1d098fcc07da7d6e79ae31e.jpg', NULL, '2022-10-12 20:56:23', NULL, '1', NULL, NULL, NULL, 5767);
INSERT INTO `media_files` VALUES ('1d0f0e6ed8a0c4a89bfd304b84599d9c', 1232141425, NULL, 'asset-icoGather.png', '001001', NULL, 'mediafiles', '2022/09/20/1d0f0e6ed8a0c4a89bfd304b84599d9c.png', '1d0f0e6ed8a0c4a89bfd304b84599d9c', '/mediafiles/2022/09/20/1d0f0e6ed8a0c4a89bfd304b84599d9c.png', NULL, '2022-09-20 21:21:28', NULL, '1', '', '002003', NULL, 8059);

