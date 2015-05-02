/*
Navicat MySQL Data Transfer

Source Server         : skywalker
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-05-02 14:37:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) NOT NULL,
  `CONTENT` longtext,
  `REPLYCOUNT` int(11) DEFAULT NULL,
  `CLICKCOUNT` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `DIGEST` varchar(32) DEFAULT NULL,
  `CATEGORYID` int(11) DEFAULT NULL,
  `TAGSSTR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6wa83kbccav2midfb6moeh168` (`CATEGORYID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('22', '天气不错', '<p>&nbsp; &nbsp; 呵呵<img src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0024.gif\" _src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0024.gif\"/> &nbsp; &nbsp; &nbsp;</p>', '2', '3', '2015-03-15 13:08:59', '呵呵', '1', '天气 心情');
INSERT INTO `article` VALUES ('23', '今天想做论坛，可是不知道何处下手', '<p>&nbsp; &nbsp;怎么办&nbsp; &nbsp;<img src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0013.gif\" _src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0013.gif\"/></p>', '2', '3', '2015-03-22 19:33:22', '怎么办', '3', '迷茫');
INSERT INTO `article` VALUES ('24', '今天改好了一个拦截器的bug', '<p>&nbsp; &nbsp;隐藏了好久<img src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0028.gif\" _src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0028.gif\"/></p>', '0', '4', '2015-04-06 17:05:56', '隐藏了好久', '3', 'bug');
INSERT INTO `article` VALUES ('25', '论坛都快写了一半了', '<p>&nbsp; &nbsp;继续，come on<img src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0081.gif\" _src=\"http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0081.gif\"/></p>', '0', '1', '2015-04-06 17:49:46', '继续，comeon', '2', 'forum');

-- ----------------------------
-- Table structure for `article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `ARTICLEID` int(11) NOT NULL,
  `TAGID` int(11) NOT NULL,
  PRIMARY KEY (`TAGID`,`ARTICLEID`),
  KEY `FK_l9lpjb7umfhxtkga58r97vkc2` (`ARTICLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES ('22', '34');
INSERT INTO `article_tag` VALUES ('22', '35');
INSERT INTO `article_tag` VALUES ('23', '36');
INSERT INTO `article_tag` VALUES ('24', '37');
INSERT INTO `article_tag` VALUES ('25', '38');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '心情');
INSERT INTO `category` VALUES ('2', '嘎嘎');
INSERT INTO `category` VALUES ('3', '心情好');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('1', '曼联吧', 'http://tieba.baidu.com/f?kw=%C2%FC%C1%AA&fr=ala0');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '<p>&nbsp; &nbsp;我的留言&nbsp; &nbsp;</p>');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `HEADPATH` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) NOT NULL,
  `ARTICLEID` int(11) DEFAULT NULL,
  `PARENTID` int(11) DEFAULT NULL,
  `MESSAGEID` int(11) DEFAULT NULL,
  `NICKNAME` varchar(255) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `HOMEPAGE` varchar(255) DEFAULT NULL,
  `REPLYTIME` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_l9rt3q9xn4fvx4gmy5v04fd4v` (`ARTICLEID`),
  KEY `FK_4hhvmax8ixs7j4qstddu2lo2u` (`PARENTID`),
  KEY `FK_sh29yykyudpkdcbyftrholc6v` (`MESSAGEID`),
  CONSTRAINT `FK_4hhvmax8ixs7j4qstddu2lo2u` FOREIGN KEY (`PARENTID`) REFERENCES `reply` (`ID`),
  CONSTRAINT `FK_l9rt3q9xn4fvx4gmy5v04fd4v` FOREIGN KEY (`ARTICLEID`) REFERENCES `article` (`ID`),
  CONSTRAINT `FK_sh29yykyudpkdcbyftrholc6v` FOREIGN KEY (`MESSAGEID`) REFERENCES `message` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('61', 'images/default-avatar.png', '呀，卡通博客啦', null, null, '1', 'skywalker', 'xsdwem7@hotmail.com', '', '2015-03-15 12:34:30');
INSERT INTO `reply` VALUES ('63', 'images/default-avatar.png', '[reply]skywalker[/reply]<br>呵呵', null, '61', '1', '主人公', 'xsdwem7@hotmail.com', '', '2015-03-15 12:38:12');
INSERT INTO `reply` VALUES ('64', 'images/default-avatar.png', 'haha', '22', null, null, 'skywalker', 'xsdwem7@hotmail.com', '', '2015-03-20 15:33:29');
INSERT INTO `reply` VALUES ('65', 'images/default-avatar.png', '[reply]skywalker[/reply]<br>呵呵哒，你也来啦', '22', '64', null, '主人公', 'xsdem7@sina.cn', '', '2015-03-22 19:30:51');
INSERT INTO `reply` VALUES ('66', 'images/default-avatar.png', '想做就做，别纠结了。。。', '23', null, null, 'skywalker', 'xsdwem7@hotmail.com', '', '2015-03-22 19:34:10');
INSERT INTO `reply` VALUES ('67', 'images/default-avatar.png', '[reply]skywalker[/reply]<br>呵呵哒，写了不少啦', '23', '66', null, 'skywalker', 'xsdwem7@hotmail.com', '', '2015-04-06 16:49:01');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('34', '天气');
INSERT INTO `tag` VALUES ('35', '心情');
INSERT INTO `tag` VALUES ('36', '迷茫');
INSERT INTO `tag` VALUES ('37', 'bug');
INSERT INTO `tag` VALUES ('38', 'forum');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(36) NOT NULL,
  `ABOUTUS` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'skywalker', '81dc9bdb52d04dc20036dbd8313ed055', '<p>					</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 大家好，我是一个屌丝程序猿</p>');
