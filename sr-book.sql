/*
Navicat MySQL Data Transfer

Source Server         : zjf
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : sr-book

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-04-15 09:39:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `bid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '书籍表',
  `sn` varchar(32) NOT NULL COMMENT 'sn',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `author` varchar(32) NOT NULL COMMENT '作者',
  `publishing` varchar(32) NOT NULL COMMENT '出版社',
  `bprice` float(255,0) DEFAULT NULL COMMENT '进价',
  `sprice` float(10,2) NOT NULL COMMENT '售价（指导价）',
  `btype` varchar(255) NOT NULL COMMENT '类别',
  `stock` int(255) DEFAULT NULL COMMENT '当前库存量',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('1', '9787111213826', 'java编程思想', 'Bruce Eckel', '机械工业出版社', '42', '86.40', '计算机与和互联网', '92');
INSERT INTO `t_book` VALUES ('2', '9787111407010', '算法导论', 'Thomas H.Cormen', '机械工业出版社', '66', '101.10', '计算机科学丛书', '20');

-- ----------------------------
-- Table structure for t_borrow
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow`;
CREATE TABLE `t_borrow` (
  `brid` bigint(20) NOT NULL AUTO_INCREMENT,
  `bid` bigint(20) NOT NULL,
  `mid` bigint(20) NOT NULL,
  `takedate` date NOT NULL,
  `returndate` date NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`brid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_borrow
-- ----------------------------
INSERT INTO `t_borrow` VALUES ('1', '2', '0', '2019-03-21', '2019-03-22', '2019-03-21 14:41:52');
INSERT INTO `t_borrow` VALUES ('2', '2', '0', '2019-03-21', '2019-03-21', '2019-03-21 14:42:47');
INSERT INTO `t_borrow` VALUES ('3', '1', '1', '2019-03-21', '2019-03-22', '2019-03-21 15:19:34');
