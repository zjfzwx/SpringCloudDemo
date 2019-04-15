/*
Navicat MySQL Data Transfer

Source Server         : zjf
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : sr-member

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-04-15 09:39:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `mid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `idno` varchar(18) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `regdate` date NOT NULL,
  `expdate` date NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('1', '张家付', '500241199510191714', '18423620516', '2019-03-21', '2019-03-30');
