/*
Navicat MySQL Data Transfer

Source Server         : bankdata
Source Server Version : 50163
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50163
File Encoding         : 65001

Date: 2017-03-25 15:04:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `aid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `a_pid` int(10) unsigned NOT NULL COMMENT '权限信息',
  `aname` varchar(50) NOT NULL COMMENT '用户名',
  `apwd` varchar(50) NOT NULL COMMENT '密码',
  `adate` datetime NOT NULL COMMENT '注册时间',
  `arealname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `asex` varchar(10) NOT NULL COMMENT '性别',
  `aremarks` text COMMENT '备注',
  PRIMARY KEY (`aid`),
  KEY `FK_a_pid` (`a_pid`),
  CONSTRAINT `FK_a_pid` FOREIGN KEY (`a_pid`) REFERENCES `permissions` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('2', '2', 'jerry', '123', '2012-06-10 09:08:38', 'jerry1', '男', null);
INSERT INTO `admins` VALUES ('3', '3', 'alise', '345', '2012-06-10 11:05:26', 'alise3', '女', '');
INSERT INTO `admins` VALUES ('4', '4', 'jackson', '123', '2012-06-15 19:45:53', 'jackson1', '女', '超级管理员');

-- ----------------------------
-- Table structure for `bloghot`
-- ----------------------------
DROP TABLE IF EXISTS `bloghot`;
CREATE TABLE `bloghot` (
  `bid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bstate` int(10) unsigned NOT NULL COMMENT '热议状态',
  `btitle` varchar(80) NOT NULL COMMENT '热议标题',
  `bimages` varchar(50) DEFAULT NULL,
  `bvote` int(10) unsigned NOT NULL COMMENT '热议投票',
  `bremarks` text COMMENT '备注',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='微博热议内容表';

-- ----------------------------
-- Records of bloghot
-- ----------------------------
INSERT INTO `bloghot` VALUES ('1', '1', '谁最有可能成为美网大满贯得主', null, '13', null);
INSERT INTO `bloghot` VALUES ('2', '1', '您最喜爱的暑期档影视作品', null, '15', null);
INSERT INTO `bloghot` VALUES ('3', '1', '12', null, '0', null);
INSERT INTO `bloghot` VALUES ('4', '0', '123', null, '0', null);
INSERT INTO `bloghot` VALUES ('5', '0', '123', '/back/upload/pic/1.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('6', '0', '12344123', '/back/upload/pic/Jellyfish.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('7', '0', '2432', '/back/upload/pic/1.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('8', '0', '123', '/back/upload/pic/1.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('9', '0', '12234', '/back/upload/pic/1.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('10', '0', '124', '/back/upload/pic/1.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('11', '0', '123', null, '0', null);
INSERT INTO `bloghot` VALUES ('12', '0', '23434234', '/back/upload/pic/1.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('13', '0', '1243', '/back/upload/pic/1.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('14', '1', '123', '/back/upload/pic/Desert.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('15', '1', '12', '/back/upload/pic/Jellyfish.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('16', '1', '34123', '/back/upload/pic/Jellyfish.jpg', '0', null);
INSERT INTO `bloghot` VALUES ('17', '1', '123', '/back/upload/pic/Jellyfish.jpg', '0', null);

-- ----------------------------
-- Table structure for `bloghotitem`
-- ----------------------------
DROP TABLE IF EXISTS `bloghotitem`;
CREATE TABLE `bloghotitem` (
  `bloghotitemid` int(2) NOT NULL AUTO_INCREMENT,
  `bitemName` varchar(80) CHARACTER SET utf8 NOT NULL,
  `bitemimage` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `bvote` int(15) NOT NULL,
  `bid` int(2) NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`bloghotitemid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of bloghotitem
-- ----------------------------
INSERT INTO `bloghotitem` VALUES ('1', '周星驰', 'face/6.jpg', '0', '1', '12');
INSERT INTO `bloghotitem` VALUES ('2', '刘德华', 'face/1.jpg', '1', '1', null);
INSERT INTO `bloghotitem` VALUES ('3', '周杰伦', 'face/2.jpg', '2', '1', null);
INSERT INTO `bloghotitem` VALUES ('4', '王敏', 'face/4.jpg', '3', '1', null);
INSERT INTO `bloghotitem` VALUES ('5', '射雕英雄传', null, '12', '2', null);
INSERT INTO `bloghotitem` VALUES ('6', '鹿鼎记', '', '13', '2', '1');
INSERT INTO `bloghotitem` VALUES ('7', '封神榜', null, '10', '2', '11');
INSERT INTO `bloghotitem` VALUES ('8', '琅琊榜', null, '18', '2', '13');

-- ----------------------------
-- Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `lid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `l_uid` int(10) unsigned NOT NULL COMMENT '用户信息',
  `lcontent` varchar(100) NOT NULL COMMENT '收藏内容',
  `ldate` datetime NOT NULL COMMENT '收藏时间',
  `limages` varchar(50) DEFAULT NULL COMMENT '收藏图片',
  `lremarks` text,
  `l_wid` int(10) NOT NULL COMMENT '收藏的微博编号',
  PRIMARY KEY (`lid`),
  KEY `FK_l_uid` (`l_uid`),
  CONSTRAINT `FK_l_uid` FOREIGN KEY (`l_uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='收藏表';

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('4', '1', '有图片', '2012-05-31 20:35:04', '/Microblog/upload/pic/n.jpg', null, '0');
INSERT INTO `collection` VALUES ('5', '1', '没有图片的微博', '2012-05-31 20:52:04', '', null, '0');
INSERT INTO `collection` VALUES ('12', '68', '我爱你，小敏', '2017-02-26 13:16:39', '/Microblog/upload/pic/Lighthouse.jpg', null, '38');
INSERT INTO `collection` VALUES ('14', '1', '小明，早上高', '2017-02-27 08:41:39', '/Microblog/upload/pic/Lighthouse.jpg', null, '35');
INSERT INTO `collection` VALUES ('15', '1', '我爱你，小米', '2017-02-27 08:50:25', '/Microblog/upload/pic/Jellyfish.jpg', null, '39');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `c_wid` int(10) unsigned NOT NULL COMMENT '对应微博信息',
  `c_uid` int(10) unsigned NOT NULL COMMENT '发送用户信息',
  `ccontent` varchar(100) NOT NULL COMMENT '评论内容',
  `cdate` datetime NOT NULL COMMENT '评论时间',
  `cremarks` text COMMENT '备注',
  `cimages` varchar(100) DEFAULT NULL COMMENT '评论图片',
  `c_cid` int(10) unsigned zerofill NOT NULL COMMENT 'c_cid为0代表是对方评论的是微博，c_cid不为0则表示回复哪一条评论',
  `flag` int(10) unsigned zerofill NOT NULL COMMENT 'flag 0代表该评论没有被删除，假如flag等于-1代表被删除',
  PRIMARY KEY (`cid`),
  KEY `FK_c_wid` (`c_wid`),
  KEY `FK_c_uid` (`c_uid`),
  CONSTRAINT `FK_c_uid` FOREIGN KEY (`c_uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `FK_c_wid` FOREIGN KEY (`c_wid`) REFERENCES `weibo` (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '2', 'hello everyone', '2012-05-24 20:51:40', null, null, '0000000000', '0000000001');
INSERT INTO `comment` VALUES ('2', '4', '1', '北风', '2012-05-24 20:51:40', null, '/Microblog/upload/pic/64a.jpg', '0000000000', '0000000000');
INSERT INTO `comment` VALUES ('3', '4', '1', '北', '2012-05-25 20:51:40', null, '/Microblog/upload/pic/64a.jpg', '0000000000', '0000000000');
INSERT INTO `comment` VALUES ('4', '4', '1', '风', '2012-05-26 20:51:40', null, null, '0000000000', '0000000000');
INSERT INTO `comment` VALUES ('5', '4', '2', 'ok!', '2012-04-26 20:51:40', null, '/Microblog/upload/pic/64a.jpg', '0000000000', '0000000000');
INSERT INTO `comment` VALUES ('6', '4', '3', 'why?', '2012-05-16 20:51:40', null, '/Microblog/upload/pic/dc.jpg', '0000000000', '0000000000');
INSERT INTO `comment` VALUES ('14', '4', '1', '美丽女人！', '2012-05-27 13:41:58', null, '/Microblog/upload/pic/68w.jpg', '0000000000', '0000000000');
INSERT INTO `comment` VALUES ('19', '17', '1', '我赞成！', '2012-05-29 21:00:06', null, '/Microblog/upload/pic/dc.jpg', '0000000000', '0000000001');
INSERT INTO `comment` VALUES ('20', '17', '1', '中国加油！', '2012-05-29 21:05:55', null, null, '0000000000', '0000000001');
INSERT INTO `comment` VALUES ('21', '17', '1', '中国成功！', '2012-05-29 21:06:10', null, '/Microblog/upload/pic/dc.jpg', '0000000000', '0000000001');
INSERT INTO `comment` VALUES ('22', '54', '1', '123', '2017-03-01 09:26:37', null, '/Microblog/upload/pic/Lighthouse.jpg', '0000000000', '0000000001');
INSERT INTO `comment` VALUES ('23', '53', '1', '这张图好漂亮', '2017-03-01 09:26:53', null, null, '0000000000', '0000000001');
INSERT INTO `comment` VALUES ('24', '55', '1', '123', '2017-03-01 14:09:13', null, null, '0000000000', '0000000000');

-- ----------------------------
-- Table structure for `percontent`
-- ----------------------------
DROP TABLE IF EXISTS `percontent`;
CREATE TABLE `percontent` (
  `pcid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pc_pid` int(10) unsigned NOT NULL COMMENT '权限名称',
  `pcitems` varchar(50) NOT NULL COMMENT '权限items',
  `pcurl` varchar(50) DEFAULT NULL COMMENT 'url',
  `pcremarks` text COMMENT '备注',
  PRIMARY KEY (`pcid`),
  KEY `FK_pc_pid` (`pc_pid`),
  CONSTRAINT `FK_pc_pid` FOREIGN KEY (`pc_pid`) REFERENCES `permissions` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='权限内容表';

-- ----------------------------
-- Records of percontent
-- ----------------------------
INSERT INTO `percontent` VALUES ('2', '2', '微博管理', null, null);
INSERT INTO `percontent` VALUES ('3', '2', '前台用户管理', null, null);
INSERT INTO `percontent` VALUES ('4', '2', '后台用户管理', null, null);
INSERT INTO `percontent` VALUES ('5', '2', '收藏管理', null, null);
INSERT INTO `percontent` VALUES ('6', '2', '好友关注度', null, null);
INSERT INTO `percontent` VALUES ('7', '2', '评论管理', null, null);
INSERT INTO `percontent` VALUES ('8', '2', '微博热议', null, null);
INSERT INTO `percontent` VALUES ('9', '3', '微博管理', null, null);
INSERT INTO `percontent` VALUES ('10', '3', '前台用户管理', null, null);
INSERT INTO `percontent` VALUES ('12', '3', '好友关注度', null, null);
INSERT INTO `percontent` VALUES ('13', '3', '评论管理', null, null);
INSERT INTO `percontent` VALUES ('15', '4', '后台用户管理', null, null);
INSERT INTO `percontent` VALUES ('26', '3', '收藏管理', null, null);
INSERT INTO `percontent` VALUES ('31', '4', '前台用户管理', null, null);

-- ----------------------------
-- Table structure for `permissions`
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `pid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) NOT NULL COMMENT '权限名称',
  `pcontent` varchar(100) NOT NULL COMMENT '权限说明',
  `premarks` text COMMENT '备注',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('2', '管理员', '全部权限', null);
INSERT INTO `permissions` VALUES ('3', '后台用户', '普通后台用户', '后台用户');
INSERT INTO `permissions` VALUES ('4', '超级管理员', '一般权限', '用户权限');

-- ----------------------------
-- Table structure for `relations`
-- ----------------------------
DROP TABLE IF EXISTS `relations`;
CREATE TABLE `relations` (
  `rid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `r_id` int(10) unsigned NOT NULL COMMENT '加关注者',
  `g_id` int(10) unsigned NOT NULL COMMENT '被加关注者',
  `rstate` int(10) unsigned NOT NULL COMMENT '关注状态',
  `remarks` text COMMENT '备注',
  PRIMARY KEY (`rid`),
  KEY `FK_r_id` (`r_id`),
  KEY `FK_g_id` (`g_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='关系表';

-- ----------------------------
-- Records of relations
-- ----------------------------
INSERT INTO `relations` VALUES ('1', '1', '2', '0', null);
INSERT INTO `relations` VALUES ('2', '1', '3', '0', null);
INSERT INTO `relations` VALUES ('3', '2', '3', '0', null);
INSERT INTO `relations` VALUES ('4', '4', '1', '1', null);
INSERT INTO `relations` VALUES ('5', '5', '1', '1', null);
INSERT INTO `relations` VALUES ('7', '1', '10', '0', null);
INSERT INTO `relations` VALUES ('8', '1', '9', '0', null);
INSERT INTO `relations` VALUES ('10', '1', '4', '1', null);
INSERT INTO `relations` VALUES ('11', '1', '6', '0', null);
INSERT INTO `relations` VALUES ('12', '2', '10', '0', null);
INSERT INTO `relations` VALUES ('13', '4', '10', '0', null);
INSERT INTO `relations` VALUES ('14', '5', '2', '0', null);
INSERT INTO `relations` VALUES ('15', '5', '2', '0', null);
INSERT INTO `relations` VALUES ('16', '5', '2', '0', null);
INSERT INTO `relations` VALUES ('17', '5', '2', '0', null);
INSERT INTO `relations` VALUES ('18', '2', '1', '0', null);
INSERT INTO `relations` VALUES ('19', '2', '4', '0', null);
INSERT INTO `relations` VALUES ('20', '1', '5', '1', null);
INSERT INTO `relations` VALUES ('21', '1', '5', '1', null);
INSERT INTO `relations` VALUES ('22', '1', '7', '0', null);
INSERT INTO `relations` VALUES ('23', '1', '15', '1', null);
INSERT INTO `relations` VALUES ('24', '1', '15', '1', null);
INSERT INTO `relations` VALUES ('25', '68', '8', '0', null);
INSERT INTO `relations` VALUES ('26', '68', '1', '0', null);
INSERT INTO `relations` VALUES ('27', '68', '2', '1', null);
INSERT INTO `relations` VALUES ('28', '68', '2', '1', null);
INSERT INTO `relations` VALUES ('29', '68', '3', '0', null);
INSERT INTO `relations` VALUES ('30', '1', '8', '0', null);
INSERT INTO `relations` VALUES ('31', '1', '11', '0', null);
INSERT INTO `relations` VALUES ('32', '1', '13', '0', null);

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL COMMENT '用户名',
  `upwd` varchar(40) NOT NULL COMMENT '密码',
  `unickname` varchar(20) NOT NULL COMMENT '昵称',
  `usex` varchar(10) NOT NULL COMMENT '性别',
  `uaddress` varchar(100) DEFAULT NULL COMMENT '用户地址',
  `udate` datetime DEFAULT NULL COMMENT '用户生日',
  `uqq` varchar(40) DEFAULT NULL COMMENT '用户qq',
  `uedu` varchar(45) DEFAULT NULL COMMENT '用户教育信息',
  `upic` varchar(45) DEFAULT NULL COMMENT '用户头像',
  `uques` varchar(45) DEFAULT NULL COMMENT '密码保护问题',
  `urealname` varchar(45) DEFAULT NULL COMMENT '真实姓名',
  `uremarks` varchar(45) DEFAULT NULL COMMENT '备注',
  `uemail` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'jerry', '123', 'jerry', '男', '天津市南开区', '2012-05-24 13:12:01', '123456789', '本科', 'face/6.jpg', '我的出生地?天津', 'hello', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('2', 'peter', '123', 'peter', '男', '天津市和平区', '2012-05-24 13:12:15', '123456780', '本科', 'face/10.jpg', '教育地?', 'hello', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('3', 'alice', '123', 'alice', '女', 'USA', '2012-05-24 13:12:15', '1345345', 'MBA', 'face/7.jpg', 'what is yourname?', 'hello', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('4', 'jackson', '123', 'jackson', '女', 'USA', '2012-05-24 13:12:15', '64576775', '专科', 'face/16.jpg', 'wharf are you doing?', 'hello', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('5', 'hello', '123', 'hello', '女', '北京 海淀', '2012-05-26 12:42:09', '867391409', '本科', 'face/8.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('6', 'hello1', '123', 'hello1', '男', '北京 朝阳', '2012-05-26 12:46:28', '867391409', '本科', 'face/9.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('7', 'hello30', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:46:28', '867391409', '本科', 'face/10.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('8', 'alive', '123', 'alive', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/9.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('9', 'hello2', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/10.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('10', 'succy', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/11.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('11', 'succy1', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/12.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('12', 'succy2', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/13.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('13', 'rose', '123', 'rose', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/14.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('14', 'succy3', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/15.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('15', 'succy4', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/16.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('16', 'succy5', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/17.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('17', 'succy6', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/18.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('18', 'rose1', '123', 'rose', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/19.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('19', 'succy7', '123', 'succy', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/20.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('20', 'hello3', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/21.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('21', 'hello4', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/22.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('22', 'andi', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/23.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('23', 'rose2', '123', 'rose', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/24.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('24', 'andi', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/25.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('25', 'andi1', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/26.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('26', 'andi2', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/27.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('27', 'andi3', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/28.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('28', 'rose3', '123', 'rose', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/29.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('29', 'andi4', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/30.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('30', 'andi5', '123', 'andi', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/31.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('31', 'hello5', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/32.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('32', 'hello6', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/33.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('33', 'rose3', '123', 'rose', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/34.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('34', 'hello7', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/35.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('35', 'hello8', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/36.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('36', 'hello9', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/37.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('37', 'hello10', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/38.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('38', 'hello11', '123', 'alive', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/39.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('39', 'hello12', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/40.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('40', 'hello13', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/41.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('41', 'hello14', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/42.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('42', 'hello15', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/43.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('43', 'alive', '123', 'alive', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/44.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('44', 'hello16', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/45.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('45', 'hello17', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/46.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('46', 'hello18', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/47.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('47', 'hello19', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/48.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('48', 'alive1', '123', 'alive', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/49.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('49', 'hello20', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/50.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('50', 'hello21', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/51.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('51', 'hello22', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/52.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('52', 'hello23', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/53.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('53', 'alive2', '123', 'alive', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/54.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('54', 'hello24', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/55.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('55', 'hello25', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/56.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('56', 'hello26', '123', 'hello2', '男', '北京 朝阳', '2012-05-26 12:57:25', '867391409', '本科', 'face/57.jpg ', '出生地?', '含笑', 'null', '45646346@qq.com');
INSERT INTO `users` VALUES ('57', '??', '123', '??', '?', '???-??', '2017-01-01 20:35:38', '1', '??', null, '', '', 'null', null);
INSERT INTO `users` VALUES ('58', '??', '123', '??', '?', '???-??', '2017-01-01 20:42:21', '1', '??', null, '', '', 'null', null);
INSERT INTO `users` VALUES ('68', '123456', '123456', '小米2134', '男', '5343535', '2017-02-25 13:31:11', '241424531', '353', null, null, '小米', 'null', '2344@qq.com');

-- ----------------------------
-- Table structure for `weibo`
-- ----------------------------
DROP TABLE IF EXISTS `weibo`;
CREATE TABLE `weibo` (
  `wid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `wcontent` varchar(100) NOT NULL COMMENT '微博信息',
  `wdate` datetime NOT NULL COMMENT '发送时间',
  `wimage` varchar(50) DEFAULT NULL COMMENT '附加图片',
  `wtimes` int(11) NOT NULL DEFAULT '0' COMMENT '发送次数',
  `w_uid` int(10) unsigned NOT NULL COMMENT '发送用户信息',
  `wremarks` text COMMENT '备注',
  `wcountcomment` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论数量',
  `w_wid` int(10) unsigned zerofill NOT NULL COMMENT '判断该微博是否是转发过来的，转发哪一条微博的',
  PRIMARY KEY (`wid`),
  KEY `FK_w_uid` (`w_uid`),
  CONSTRAINT `FK_w_uid` FOREIGN KEY (`w_uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='微博信息表';

-- ----------------------------
-- Records of weibo
-- ----------------------------
INSERT INTO `weibo` VALUES ('1', '今天我在这里做展示', '2012-05-24 17:56:47', '/Microblog/upload/pic/64a.jpg', '5', '1', 'null', '1', '0000000000');
INSERT INTO `weibo` VALUES ('2', 'hello', '2012-05-24 17:57:05', '/Microblog/upload/pic/64fj.jpg', '2', '1', 'null', '0', '0000000000');
INSERT INTO `weibo` VALUES ('3', '中国', '2012-05-24 17:57:26', null, '2', '1', 'null', '0', '0000000000');
INSERT INTO `weibo` VALUES ('4', '北风网', '2012-05-24 17:58:29', null, '10', '2', 'null', '7', '0000000000');
INSERT INTO `weibo` VALUES ('17', '黄岩岛', '2012-05-29 11:36:37', '/Microblog/upload/pic/s.jpg', '2', '1', 'null', '3', '0000000000');
INSERT INTO `weibo` VALUES ('20', '发布第一个微博信息！', '2012-05-30 19:58:58', '/Microblog/upload/pic/c.jpg', '2', '1', 'null', '0', '0000000000');
INSERT INTO `weibo` VALUES ('25', '没有图片的微博', '2012-05-30 20:15:46', null, '2', '1', 'null', '0', '0000000000');
INSERT INTO `weibo` VALUES ('26', '有图片', '2012-05-30 20:15:56', '/Microblog/upload/pic/n.jpg', '2', '1', 'null', '0', '0000000000');
INSERT INTO `weibo` VALUES ('27', 'hel', '2012-05-30 20:15:56', null, '3', '1', 'null', '0', '0000000000');
INSERT INTO `weibo` VALUES ('33', '小米，我拍你', '2017-02-25 14:12:13', '/Microblog/upload/pic/Jellyfish.jpg', '2', '68', null, '0', '0000000000');
INSERT INTO `weibo` VALUES ('39', '我爱你，小米', '2017-02-27 08:50:21', '/Microblog/upload/pic/Jellyfish.jpg', '5', '1', null, '0', '0000000000');
INSERT INTO `weibo` VALUES ('40', '我爱你，小米', '2017-02-28 11:33:41', '/Microblog/upload/pic/Jellyfish.jpg', '4', '1', null, '0', '0000000039');
INSERT INTO `weibo` VALUES ('41', '我爱你，小米', '2017-02-28 11:33:53', '/Microblog/upload/pic/Jellyfish.jpg', '4', '1', null, '0', '0000000039');
INSERT INTO `weibo` VALUES ('42', '我爱你，小米', '2017-02-28 11:35:02', '/Microblog/upload/pic/Jellyfish.jpg', '4', '1', null, '0', '0000000039');
INSERT INTO `weibo` VALUES ('43', '我爱你，小米', '2017-02-28 11:35:07', '/Microblog/upload/pic/Jellyfish.jpg', '4', '1', null, '0', '0000000039');
INSERT INTO `weibo` VALUES ('44', '我爱你，小米', '2017-02-28 11:35:16', '/Microblog/upload/pic/Jellyfish.jpg', '4', '1', null, '0', '0000000039');
INSERT INTO `weibo` VALUES ('45', '北风网', '2017-02-28 11:35:26', null, '10', '1', null, '0', '0000000004');
INSERT INTO `weibo` VALUES ('46', '北风网', '2017-02-28 11:41:36', null, '10', '1', null, '0', '0000000004');
INSERT INTO `weibo` VALUES ('47', '北风网', '2017-02-28 11:41:50', null, '10', '1', null, '0', '0000000004');
INSERT INTO `weibo` VALUES ('48', '北风网', '2017-02-28 11:46:03', null, '10', '1', null, '0', '0000000004');
INSERT INTO `weibo` VALUES ('49', '北风网', '2017-02-28 11:46:10', null, '10', '1', null, '0', '0000000004');
INSERT INTO `weibo` VALUES ('50', '北风网', '2017-02-28 11:47:40', null, '10', '1', null, '0', '0000000004');
INSERT INTO `weibo` VALUES ('51', '北风网', '2017-02-28 12:10:20', null, '10', '1', null, '0', '0000000004');
INSERT INTO `weibo` VALUES ('52', 'hel', '2017-02-28 12:10:36', null, '3', '1', null, '0', '0000000027');
INSERT INTO `weibo` VALUES ('53', '今天我在这里做展示', '2017-02-28 12:10:49', '/Microblog/upload/pic/64a.jpg', '5', '1', null, '0', '0000000001');
INSERT INTO `weibo` VALUES ('54', '今天我在这里做展示', '2017-02-28 12:25:31', '/Microblog/upload/pic/64a.jpg', '5', '1', null, '0', '0000000001');
INSERT INTO `weibo` VALUES ('55', '今天我在这里做展示', '2017-03-01 12:16:28', '/Microblog/upload/pic/64a.jpg', '5', '1', null, '0', '0000000001');
INSERT INTO `weibo` VALUES ('56', '1243', '2017-03-25 13:28:09', '/Microblog/upload/pic/1.jpg', '0', '1', null, '0', '0000000000');

-- ----------------------------
-- Procedure structure for `hel`
-- ----------------------------
DROP PROCEDURE IF EXISTS `hel`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `hel`()
BEGIN
  select * from users ;
END
;;
DELIMITER ;
