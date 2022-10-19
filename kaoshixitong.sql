/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : kaoshixitong

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 19/10/2022 23:04:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for configure
-- ----------------------------
DROP TABLE IF EXISTS `configure`;
CREATE TABLE `configure`  (
  `knowledges` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sxamount` int(11) NULL DEFAULT NULL,
  `sxscore` int(11) NULL DEFAULT NULL,
  `mxamount` int(11) NULL DEFAULT NULL,
  `mxscore` int(11) NULL DEFAULT NULL,
  `tkamount` int(11) NULL DEFAULT NULL,
  `tkscore` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of configure
-- ----------------------------
INSERT INTO `configure` VALUES ('1', 1, 1, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for danxuan
-- ----------------------------
DROP TABLE IF EXISTS `danxuan`;
CREATE TABLE `danxuan`  (
  `number` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `diff` int(2) NULL DEFAULT NULL,
  `knowledge` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of danxuan
-- ----------------------------
INSERT INTO `danxuan` VALUES (1, '段宇辉666', 1, '1', 'A');

-- ----------------------------
-- Table structure for mxuan
-- ----------------------------
DROP TABLE IF EXISTS `mxuan`;
CREATE TABLE `mxuan`  (
  `number` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `diff` int(2) NULL DEFAULT NULL,
  `knowledge` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mxuan
-- ----------------------------
INSERT INTO `mxuan` VALUES (1, '这是多选题', 1, '1', 'AB');

-- ----------------------------
-- Table structure for paperlog
-- ----------------------------
DROP TABLE IF EXISTS `paperlog`;
CREATE TABLE `paperlog`  (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `paper` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `useranswer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paperlog
-- ----------------------------
INSERT INTO `paperlog` VALUES ('1', '1', '1');
INSERT INTO `paperlog` VALUES ('user2', '{\"allst\":[{\"bh\":[1],\"score\":1,\"type\":1},{\"bh\":[1],\"score\":1,\"type\":2},{\"bh\":[1],\"score\":1,\"type\":3}]}', '[[\"A\"],[\"AB\"],[\"1\"]]');

-- ----------------------------
-- Table structure for tiankong
-- ----------------------------
DROP TABLE IF EXISTS `tiankong`;
CREATE TABLE `tiankong`  (
  `number` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `diff` int(2) NULL DEFAULT NULL,
  `knowledge` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tiankong
-- ----------------------------
INSERT INTO `tiankong` VALUES (1, '扣1送地狱火', 1, '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `myname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '段宇辉', 100);

SET FOREIGN_KEY_CHECKS = 1;
