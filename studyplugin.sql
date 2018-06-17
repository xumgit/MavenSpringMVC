/*
Navicat MySQL Data Transfer

Source Server         : SmartInstall
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : studyplugin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-04-13 16:24:11
*/

CREATE SCHEMA IF NOT EXISTS `studyplugin` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE studyplugin;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `apppackage`
-- ----------------------------
-- DROP TABLE IF EXISTS `apppackage`;
CREATE TABLE IF NOT EXISTS `apppackage` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `platform` varchar(45) NOT NULL,
  `value` longtext NOT NULL,
  `number` int(5) NOT NULL,
  `size` varchar(45) NOT NULL,
  `lastEdit` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apppackage
-- ----------------------------
drop procedure if exists insert_apppackage;  
DELIMITER $$
create procedure insert_apppackage() begin 
	if not exists (select apppackage.id from apppackage where apppackage.id = 3) then
		INSERT INTO `apppackage` VALUES ('3', 'abc', '2016 MS', 'test2', '11', '1023KB', '27/02/2018:11:46');
		INSERT INTO `apppackage` VALUES ('4', 'def', '2016 SS', 'test3', '24', '1345 KB', '06/03/2018:02:16');
		INSERT INTO `apppackage` VALUES ('5', 'ghi', '2016 MS', 'test4', '36', '345KB', '06/03/2018:02:49');
		INSERT INTO `apppackage` VALUES ('6', 'jkl', '2016 MS', 'test5', '53', '45 KB', '07/03/2018:10:58');
		INSERT INTO `apppackage` VALUES ('7', 'mno', '2016 SS', 'test6', '72', '2367KB', '17/03/2018:03:13');
		INSERT INTO `apppackage` VALUES ('8', 'qrs', '2016 MS', 'test7', '15', '3857KB', '17/03/2018:03:52');
		INSERT INTO `apppackage` VALUES ('9', 'tuv', '2016 SS', 'test8', '62', '458 KB', '17/03/2018:03:57');
		INSERT INTO `apppackage` VALUES ('10', 'wxy', '2016 MS', 'test9', '18', '36 KB', '17/03/2018:03:58');
	end if;
END $$
CALL insert_apppackage() $$
DELIMITER ;
-- ----------------------------
-- Table structure for `author`
-- ----------------------------
-- DROP TABLE IF EXISTS `author`;
CREATE TABLE IF NOT EXISTS `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `clone` varchar(8000) DEFAULT '{"CloneItemStatus":"Unknown","TVUniqueID":"Unknown","UpgradeData":"Unknown"}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author
-- ----------------------------
drop procedure if exists insert_author;  
DELIMITER $$
create procedure insert_author() begin
	if not exists (select * from information_schema.columns where table_name = 'author' and column_name = 'clone') then  
   		ALTER TABLE `author` ADD COLUMN `clone` varchar(8000) DEFAULT '{"CloneItemStatus":"Unknown","TVUniqueID":"Unknown","UpgradeData":"Unknown"}' AFTER `country`;
	end if;
	if not exists (select author.id from author where author.id = 1) then
		INSERT INTO `author` VALUES ('1', 'zhangshan', '25', 'zhangshan@100.com', 'France', '{"CloneItemStatus":"Unknown","TVUniqueID":"Unknown","UpgradeData":"Unknown"}');
		INSERT INTO `author` VALUES ('2', 'lishi', '16', 'lishi@125.com', 'Gremany', '{"CloneItemStatus":"Unknown","TVUniqueID":"Unknown","UpgradeData":"Unknown"}');
		INSERT INTO `author` VALUES ('3', 'wangwu', '37', 'ww@278.com', 'China', '{"CloneItemStatus":"Unknown","TVUniqueID":"Unknown","UpgradeData":"Unknown"}');
	end if;
END $$
CALL insert_author() $$
DELIMITER ;
-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
-- DROP TABLE IF EXISTS `blog`;
CREATE TABLE IF NOT EXISTS `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorid` int(11) DEFAULT NULL,
  `title` varchar(35) DEFAULT NULL,
  `mainbody` text,
  `creattime` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
drop procedure if exists insert_blog;  
DELIMITER $$
create procedure insert_blog() begin 
	if not exists (select blog.id from blog where blog.id = 1) then
		INSERT INTO `blog` VALUES ('1', '1', 'fruit', 'this is good fruit.', '2018-04-06 10:11:11');
		INSERT INTO `blog` VALUES ('2', '1', 'sport', 'good sport games.', '2018-04-08 13:12:16');
		INSERT INTO `blog` VALUES ('3', '1', 'sky', 'the sky is perfect blue.', '2018-04-11 16:26:39');
		INSERT INTO `blog` VALUES ('4', '2', 'eat', 'delicious food, good good.', '2018-04-11 17:48:25');
	end if;
END $$
CALL insert_blog() $$
DELIMITER ;
-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
-- DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blogid` int(11) DEFAULT NULL,
  `content` text,
  `creattime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
drop procedure if exists insert_comment;  
DELIMITER $$
create procedure insert_comment() begin 
	if not exists (select comment.id from comment where comment.id = 1) then
		INSERT INTO `comment` VALUES ('1', '3', 'you say right, it was a perfect day.', '2018-04-11 16:56:47');
		INSERT INTO `comment` VALUES ('2', '4', 'very delicious food...', '2018-04-11 17:50:35');
	end if;
END $$
CALL insert_comment() $$
DELIMITER ;
-- ----------------------------
-- Table structure for `plugin`
-- ----------------------------
-- DROP TABLE IF EXISTS `plugin`;
CREATE TABLE IF NOT EXISTS `plugin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` varchar(100) DEFAULT NULL,
  `received` varchar(100) DEFAULT NULL,
  `link` varchar(120) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `hidden` char(110) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plugin
-- ----------------------------
drop procedure if exists insert_plugin;  
DELIMITER $$
create procedure insert_plugin() begin 
	if not exists (select plugin.id from plugin where plugin.id = 1) then
		INSERT INTO `plugin` VALUES ('1', 'sendertest', 'xyzTest', 'link1', 'status1', 'hidden1');
		INSERT INTO `plugin` VALUES ('2', 'seb', 'opqPlugin', 'link2', 'status2', 'hidden2');
		INSERT INTO `plugin` VALUES ('3', 'xrt', 'rtyui', 'link3', 'status3', 'hidden3');
		INSERT INTO `plugin` VALUES ('4', 'tgh', 'cvbhj', 'link4', 'status4', 'hidden4');
		INSERT INTO `plugin` VALUES ('5', 'nmo', 'sgko', 'link5', 'status5', 'hidden5');
	end if;
END $$
CALL insert_plugin() $$
DELIMITER ;
-- ----------------------------
-- Table structure for `test`
-- ----------------------------
-- DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `id` int(11) NOT NULL,
  `received` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
drop procedure if exists insert_test;  
DELIMITER $$
create procedure insert_test() begin 
	if not exists (select test.id from test where test.id = 3) then
		INSERT INTO `test` VALUES ('3', 'tuoTest', 'fu', 'fujian');
		INSERT INTO `test` VALUES ('4', 'xyzTest', 'shang', 'shanghai');
	end if;
END $$
CALL insert_test() $$
DELIMITER ;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
-- DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `secondName` varchar(50) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `height` int(5) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
drop procedure if exists insert_user;  
DELIMITER $$
create procedure insert_user() begin 
	if not exists (select user.id from user where user.id = 1) then
		INSERT INTO `user` VALUES ('1', 'abc', 'ccc', '17', '180', '2015-04-02');
		INSERT INTO `user` VALUES ('2', 'ghi', 'iii', '26', '178', '2006-02-16');
		INSERT INTO `user` VALUES ('3', 'tuv', 'vvv', '23', '168', '2003-12-05');
	end if;
END $$
CALL insert_user() $$
DELIMITER ;

-- ----------------------------
-- Table structure for `upload`
-- ----------------------------
-- DROP TABLE IF EXISTS `upload`;
CREATE TABLE IF NOT EXISTS `upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filename` text,
  `contenttype` text,
  `filedata` blob,
  `isempty` enum('true','false') DEFAULT 'false',
  `size` int(11) DEFAULT NULL,
  `property` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;