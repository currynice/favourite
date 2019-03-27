/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : collection

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-03-27 16:24:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg` varchar(255) NOT NULL,
  `online_count` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `username` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat
-- ----------------------------

-- ----------------------------
-- Table structure for collector_view
-- ----------------------------
DROP TABLE IF EXISTS `collector_view`;
CREATE TABLE `collector_view` (
  `id` bigint(20) NOT NULL,
  `counts` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collector_view
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `created_date` bigint(20) NOT NULL,
  `entity_id` bigint(20) NOT NULL,
  `entity_type` varchar(255) NOT NULL,
  `status` int(11) DEFAULT '0',
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('11', '评论1', '1552894134323', '3', 'News', '0', '1');
INSERT INTO `comment` VALUES ('12', '评论2', '1552894134526', '6', 'News', '0', '2');
INSERT INTO `comment` VALUES ('13', '评论3', '1552894134581', '6', 'News', '0', '3');
INSERT INTO `comment` VALUES ('14', '评论4', '1552894134635', '7', 'News', '0', '4');
INSERT INTO `comment` VALUES ('15', '评论5', '1552894134677', '7', 'News', '0', '5');
INSERT INTO `comment` VALUES ('16', '评论6', '1552894134724', '2', 'News', '1', '6');
INSERT INTO `comment` VALUES ('17', '评论7', '1552894134790', '2', 'News', '0', '7');
INSERT INTO `comment` VALUES ('18', '评论8', '1552894134846', '2', 'News', '0', '8');
INSERT INTO `comment` VALUES ('19', '评论9', '1552894134901', '2', 'News', '0', '9');
INSERT INTO `comment` VALUES ('20', '评论10', '1552894134942', '2', 'News', '0', '10');
INSERT INTO `comment` VALUES ('21', '评论1', '1553478387309', '2', 'News', '0', '1');
INSERT INTO `comment` VALUES ('22', '评论2', '1553478387638', '2', 'News', '0', '2');
INSERT INTO `comment` VALUES ('23', '评论3', '1553478387739', '2', 'News', '0', '3');
INSERT INTO `comment` VALUES ('24', '评论4', '1553478387879', '2', 'News', '0', '4');
INSERT INTO `comment` VALUES ('25', '评论5', '1553478388021', '2', 'News', '0', '5');
INSERT INTO `comment` VALUES ('26', '评论6', '1553478388158', '2', 'News', '0', '6');
INSERT INTO `comment` VALUES ('27', '评论7', '1553478388329', '2', 'News', '0', '7');
INSERT INTO `comment` VALUES ('28', '评论8', '1553478388420', '2', 'News', '0', '8');
INSERT INTO `comment` VALUES ('29', '评论9', '1553478388468', '2', 'News', '0', '9');
INSERT INTO `comment` VALUES ('30', '评论10', '1553478388519', '2', 'News', '0', '10');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) NOT NULL,
  `follow_id` bigint(20) NOT NULL,
  `last_modify_time` bigint(20) NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------

-- ----------------------------
-- Table structure for good_infos
-- ----------------------------
DROP TABLE IF EXISTS `good_infos`;
CREATE TABLE `good_infos` (
  `tg_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `tg_title` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '商品标题',
  `tg_price` decimal(8,2) DEFAULT NULL COMMENT '商品单价',
  `tg_unit` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '单位',
  `tg_order` varchar(255) DEFAULT NULL COMMENT '排序',
  `tg_type_id` int(11) DEFAULT NULL COMMENT '类型外键编号',
  PRIMARY KEY (`tg_id`),
  KEY `tg_type_id` (`tg_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of good_infos
-- ----------------------------
INSERT INTO `good_infos` VALUES ('1', '金针菇', '5.50', '斤', '1', '3');
INSERT INTO `good_infos` VALUES ('2', '油菜', '12.60', '斤', '2', '1');

-- ----------------------------
-- Table structure for good_types
-- ----------------------------
DROP TABLE IF EXISTS `good_types`;
CREATE TABLE `good_types` (
  `tgt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `tgt_name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '类型名称',
  `tgt_is_show` char(1) DEFAULT NULL COMMENT '是否显示',
  `tgt_order` int(2) DEFAULT NULL COMMENT '类型排序',
  PRIMARY KEY (`tgt_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of good_types
-- ----------------------------
INSERT INTO `good_types` VALUES ('1', '绿色蔬菜', '1', '1');
INSERT INTO `good_types` VALUES ('2', '根茎类', '1', '2');
INSERT INTO `good_types` VALUES ('3', '菌类', '1', '3');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');

-- ----------------------------
-- Table structure for in_mail
-- ----------------------------
DROP TABLE IF EXISTS `in_mail`;
CREATE TABLE `in_mail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `conversation_id` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `from_id` bigint(20) NOT NULL,
  `to_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bbgn5eitkowfm8k3jttfx5vnb` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of in_mail
-- ----------------------------

-- ----------------------------
-- Table structure for login_ticket
-- ----------------------------
DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE `login_ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expired` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `ticket` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_ticket
-- ----------------------------
INSERT INTO `login_ticket` VALUES ('14', '1552705368997', '1', 'aed3d49e5cff47ebae9b3d0c47f3d7de', '20');
INSERT INTO `login_ticket` VALUES ('15', '1552705833364', '1', 'af27ffad0f6044558feb768b4ced8c32', '20');
INSERT INTO `login_ticket` VALUES ('16', '1552726655155', '1', '5619e5290bec4d7c89b1ce7e01edf17a', '24');
INSERT INTO `login_ticket` VALUES ('17', '1552727772493', '1', '4751064af85948b185db31c10ad3ec05', '20');
INSERT INTO `login_ticket` VALUES ('18', '1552728185850', '0', 'ffed720b2b04409282a74c0cac425dce', '20');
INSERT INTO `login_ticket` VALUES ('19', '1552882346059', '0', 'ea70f20ca6694f69b8ac2e31efbd5df9', '24');
INSERT INTO `login_ticket` VALUES ('20', '1552963514800', '1', '96f2cebeafd348e783efc5bd525e7470', '20');
INSERT INTO `login_ticket` VALUES ('21', '1552963974839', '0', '70fba35a80b542bb9765cec3d1f8a113', '20');
INSERT INTO `login_ticket` VALUES ('22', '1552966655163', '0', '09268a0056484e49b7622f9b68c02fdc', '20');
INSERT INTO `login_ticket` VALUES ('23', '1553335457787', '0', '73ccc0932e2742f1a3654a42f99d25b9', '24');
INSERT INTO `login_ticket` VALUES ('24', '2222222', '0', 'xxxxx11', '1');
INSERT INTO `login_ticket` VALUES ('25', '1553659369287', '0', 'aa2871860f1e4591849710ce7a7c9489', '20');
INSERT INTO `login_ticket` VALUES ('26', '1553663534707', '0', '2ad8fcf33fd949918aba66108a9d1090', '20');
INSERT INTO `login_ticket` VALUES ('27', '1553668072564', '0', '142f3356f654443f9a646c8f4d05f025', '20');
INSERT INTO `login_ticket` VALUES ('28', '1553670498786', '0', '1c7c2dc1f73f4744a0a20917c7693b5f', '20');
INSERT INTO `login_ticket` VALUES ('29', '1553673401883', '0', '25a830f939fe47918da05cdba8668b71', '20');
INSERT INTO `login_ticket` VALUES ('30', '1553674680097', '0', '4d316ebf935e465bbdaed8fef1f485e6', '20');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_count` int(11) NOT NULL DEFAULT '0',
  `created_date` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `like_count` int(11) NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '3', '1552631645561', '', '2', 'new1', '22', '内容');
INSERT INTO `news` VALUES ('2', '0', '1552635695561', '', '0', 'new2', '19', '内容');
INSERT INTO `news` VALUES ('3', '0', '1552631695534', '', '0', 'new3', '19', '内容');
INSERT INTO `news` VALUES ('4', '0', '1552631395561', '', '0', 'new4', '19', '内容');
INSERT INTO `news` VALUES ('5', '0', '1552631695544', '', '0', 'new5', '22', '内容');
INSERT INTO `news` VALUES ('6', '0', '1552631694344', '', '0', 'new6', '22', '内容');
INSERT INTO `news` VALUES ('7', '0', '1552631694562', '', '0', 'new7', '21', '内容');
INSERT INTO `news` VALUES ('8', '0', '1552631695567', '', '0', 'new8', '21', '内容');
INSERT INTO `news` VALUES ('9', '0', '1552631695456', '', '0', 'new9', '21', '内容');
INSERT INTO `news` VALUES ('10', '0', '1552263169456', 'xxx', '0', 'new10', '23', '内容');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `last_modify_time` bigint(20) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `resource_name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eqidcj5580iyqinupthsc3fjt` (`resource_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) NOT NULL,
  `des` varchar(255) NOT NULL,
  `last_modify_time` bigint(20) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `selected` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `t_name` varchar(30) DEFAULT NULL COMMENT '名称',
  `t_age` int(10) DEFAULT NULL COMMENT '年龄',
  `t_address` varchar(100) DEFAULT NULL COMMENT '家庭住址',
  `t_pwd` varchar(100) CHARACTER SET latin1 DEFAULT NULL COMMENT '用户登录密码',
  PRIMARY KEY (`t_id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('17', 'curry', '24', 'bj', '56');
INSERT INTO `t_user` VALUES ('21', 'df', '24', 'nj', '8');
INSERT INTO `t_user` VALUES ('20', 'cxy', '18', 'nj', '23');
INSERT INTO `t_user` VALUES ('22', 'edf', '56', 'nj', '89');

-- ----------------------------
-- Table structure for url_library
-- ----------------------------
DROP TABLE IF EXISTS `url_library`;
CREATE TABLE `url_library` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT '0',
  `logo_url` varchar(300) DEFAULT NULL,
  `url` varchar(600) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of url_library
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `background_picture` varchar(255) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `introduction` text,
  `last_modify_time` bigint(20) DEFAULT NULL,
  `my_picture` varchar(255) DEFAULT NULL,
  `out_date` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `validata_code` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('18', null, '1552630987723', '69490005984@qq.com', null, '1552630987723', '/img/favicon.png', null, '2B512DA971973B87877F37BE9299458E', 'd3fb77', 'cxy67', null, null, null);
INSERT INTO `user` VALUES ('19', null, '1552631279848', '67887979@qq.com', null, '1552631279848', '/img/favicon.png', null, 'C4529676E494836D740E943C33F9CC1A', 'c365f3', 'cxy', null, null, null);
INSERT INTO `user` VALUES ('20', null, '1552631695561', '613719@qq.com', null, '1552631695561', '/img/favicon.png', null, '4477B5F543C0857E35E63C49C3590EE5', 'a2eac5', 'cxy2', null, null, null);
INSERT INTO `user` VALUES ('21', null, '1552633405720', '111@qq.com', null, '1552633405720', '/img/favicon.png', null, '80FC9C310EFDCBE2ADBD4FF878A57D7E', '7474ff', 'cxy30', null, null, null);
INSERT INTO `user` VALUES ('22', null, '1552639366769', '999@qq.com', null, '1552639366769', '/img/favicon.png', null, 'D8847E7909C57F298B59FFB01A2C55C1', 'cb2940', 'cxy77', null, null, null);
INSERT INTO `user` VALUES ('23', null, '1552639716284', '8884@qq.com', null, '1552639716284', '/img/favicon.png', null, 'D4FA5513FBA025C8523B7A3FA19FA8AA', '683ead', 'cxy00', null, null, null);
INSERT INTO `user` VALUES ('24', null, '1552724062884', '694975984@qq.com', null, '1552724062884', '/img/favicon.png', null, '43B0E5F7A95CA2C36F3F726FBB7E5FA5', '079276', '程新宇22', null, null, null);

-- ----------------------------
-- Table structure for user_is_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_is_follow`;
CREATE TABLE `user_is_follow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_follow` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_is_follow
-- ----------------------------
