/*
 Navicat Premium Data Transfer

 Source Server         : aliyun_32bit
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 39.108.85.212:3306
 Source Schema         : woke

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 11/07/2018 11:04:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for baoming
-- ----------------------------
DROP TABLE IF EXISTS `baoming`;
CREATE TABLE `baoming`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tel` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of baoming
-- ----------------------------
INSERT INTO `baoming` VALUES (106, '网站', '18856321432', '', '');
INSERT INTO `baoming` VALUES (107, '杨总', '15321467896', '', '');
INSERT INTO `baoming` VALUES (110, '杨杨总', '15632145678', '', '');
INSERT INTO `baoming` VALUES (111, '好的话', '15863214567', '', '');
INSERT INTO `baoming` VALUES (112, '好的', '15863214598', '', '');

-- ----------------------------
-- Table structure for guanggao
-- ----------------------------
DROP TABLE IF EXISTS `guanggao`;
CREATE TABLE `guanggao`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `centent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datetime` date NOT NULL,
  `count` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guanggao
-- ----------------------------
INSERT INTO `guanggao` VALUES (113, '5.jpg', '8.jpg', '水瓶座', '选择', '2018-06-30', 20);
INSERT INTO `guanggao` VALUES (114, '11.jpg', '11.jpg', '龙', '选择', '2018-06-02', 49);
INSERT INTO `guanggao` VALUES (112, '1.jpg', '2.jpg', '龙', '选择', '2018-07-29', 53);
INSERT INTO `guanggao` VALUES (115, '5.jpg', '7.jpg', '龙', '杨号', '2018-07-08', 67);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, 'admin', '123456', '管理员');

SET FOREIGN_KEY_CHECKS = 1;
