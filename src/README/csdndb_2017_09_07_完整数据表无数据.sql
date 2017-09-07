/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : csdndb

Target Server Type    : MYSQL
Target Server Version : 50517
File Encoding         : 65001

Date: 2017-09-07 17:30:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `author`
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` varchar(100) NOT NULL,
  `id_author` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `headImg` varchar(255) DEFAULT NULL,
  `viewNums` varchar(10) DEFAULT NULL,
  `points` varchar(10) DEFAULT NULL,
  `rank` varchar(10) DEFAULT NULL,
  `originalNums` varchar(10) DEFAULT NULL,
  `repuishNums` varchar(10) DEFAULT NULL,
  `translateNums` varchar(10) DEFAULT NULL,
  `commentNums` varchar(10) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `descb` varchar(500) DEFAULT NULL,
  `isBlogExpert` tinyint(1) NOT NULL,
  `isPreBlogExpert` tinyint(1) NOT NULL,
  `isPersist` tinyint(1) NOT NULL,
  `isColumnUp` tinyint(1) NOT NULL,
  `isBlogStars` tinyint(1) NOT NULL,
  `isMicrMvp` tinyint(1) NOT NULL,
  `blogColumns` text,
  `createTime` varchar(100) DEFAULT NULL,
  `stamp` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `spare1` varchar(255) DEFAULT NULL,
  `spare2` varchar(255) DEFAULT NULL,
  `fromExpert` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_author` (`id_author`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author
-- ----------------------------

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` varchar(50) NOT NULL,
  `id_blog` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `isTop` int(2) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `detailsUrl` varchar(255) DEFAULT NULL,
  `publishDateTime` varchar(50) DEFAULT NULL,
  `viewNums` int(10) DEFAULT NULL,
  `commentNums` int(10) DEFAULT NULL,
  `summary` text,
  `details` text,
  `upNums` int(10) DEFAULT NULL,
  `downNums` int(10) DEFAULT NULL,
  `id_author` varchar(50) DEFAULT NULL,
  `createTime` varchar(50) DEFAULT NULL,
  `stamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `spare1` varchar(255) DEFAULT NULL,
  `spare2` varchar(255) DEFAULT NULL,
  `fromExpert` tinyint(2) DEFAULT NULL,
  `headImg` varchar(255) DEFAULT NULL,
  `typeId` tinyint(4) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_blog` (`id_blog`) USING BTREE,
  KEY `id_author` (`id_author`) USING BTREE,
  KEY `commentNums` (`commentNums`) USING BTREE,
  KEY `viewNums` (`viewNums`) USING BTREE,
  KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for `expert`
-- ----------------------------
DROP TABLE IF EXISTS `expert`;
CREATE TABLE `expert` (
  `id` varchar(255) NOT NULL,
  `id_expert` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `headImg` varchar(255) DEFAULT NULL,
  `localtion` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `articleNums` varchar(10) DEFAULT NULL,
  `readNums` varchar(10) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  `stamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `spare1` varchar(255) DEFAULT NULL,
  `spare2` varchar(255) DEFAULT NULL,
  `typeId` tinyint(4) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_expert` (`id_expert`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expert
-- ----------------------------

-- ----------------------------
-- Table structure for `preference`
-- ----------------------------
DROP TABLE IF EXISTS `preference`;
CREATE TABLE `preference` (
  `id` varchar(100) NOT NULL,
  `id_blog` varchar(255) DEFAULT NULL,
  `id_csdn` varchar(255) DEFAULT NULL,
  `type` tinyint(2) DEFAULT NULL COMMENT '是否喜欢该文章，1：喜欢；2：不喜欢',
  `createTime` varchar(255) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of preference
-- ----------------------------

-- ----------------------------
-- Table structure for `pstar`
-- ----------------------------
DROP TABLE IF EXISTS `pstar`;
CREATE TABLE `pstar` (
  `id` varchar(100) NOT NULL,
  `id_blog` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `id_csdn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pstar
-- ----------------------------

-- ----------------------------
-- Table structure for `puser`
-- ----------------------------
DROP TABLE IF EXISTS `puser`;
CREATE TABLE `puser` (
  `id` varchar(100) NOT NULL,
  `id_csdn` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `avatarAddr` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `stamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `spare1` varchar(255) DEFAULT NULL,
  `spare2` varchar(255) DEFAULT NULL,
  `dislikeType` varchar(255) DEFAULT NULL,
  `praise` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of puser
-- ----------------------------
