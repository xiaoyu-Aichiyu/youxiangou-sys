/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : yxgsys

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2022-11-09 09:20:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `address` varchar(100) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';

-- ----------------------------
-- Records of t_address
-- ----------------------------

-- ----------------------------
-- Table structure for `t_carousel`
-- ----------------------------
DROP TABLE IF EXISTS `t_carousel`;
CREATE TABLE `t_carousel` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `carousel_img` varchar(100) DEFAULT NULL COMMENT '轮播图',
  `carousel_src` varchar(100) DEFAULT NULL COMMENT '轮播路径',
  `carousel_title` varchar(100) DEFAULT NULL COMMENT '轮播标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播表';

-- ----------------------------
-- Records of t_carousel
-- ----------------------------

-- ----------------------------
-- Table structure for `t_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_name` varchar(100) DEFAULT NULL COMMENT '优惠券名',
  `coupon_money` int(11) DEFAULT NULL COMMENT '优惠金额',
  `coupon_rebate` double DEFAULT NULL COMMENT '优惠折扣',
  `coupon_term` int(11) DEFAULT NULL COMMENT '优惠条件',
  `start_time` datetime DEFAULT NULL COMMENT '优惠开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '优惠结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠卷表';

-- ----------------------------
-- Records of t_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `t_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `item_name` varchar(20) DEFAULT NULL COMMENT '商品品牌',
  `item_title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `item_img` varchar(100) DEFAULT NULL COMMENT '商品图片',
  `item_note` varchar(100) DEFAULT NULL COMMENT '商品备注',
  `item_price` double DEFAULT NULL COMMENT '价格',
  `item_num` int(11) DEFAULT NULL COMMENT '库存数量',
  `sale_num` int(11) DEFAULT NULL COMMENT '销售数量',
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  `modified_time` date DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改用户',
  `type_id` int(11) DEFAULT NULL COMMENT '类型id',
  `item_state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of t_item
-- ----------------------------

-- ----------------------------
-- Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(20) DEFAULT NULL COMMENT '执行操作',
  `method` varchar(200) DEFAULT NULL COMMENT '执行方法',
  `params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) DEFAULT NULL COMMENT '执行时间',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志表';

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(20) DEFAULT NULL COMMENT '菜单名',
  `menu_note` varchar(20) DEFAULT NULL COMMENT '菜单备注',
  `menu_type` int(11) DEFAULT NULL COMMENT '类型 1：按钮 2：菜单',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_content` varchar(200) DEFAULT NULL COMMENT '公告内容',
  `notice_time` datetime DEFAULT NULL COMMENT '公告时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

-- ----------------------------
-- Records of t_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total_price` int(11) DEFAULT NULL COMMENT '总价',
  `coupon_price` int(11) DEFAULT NULL COMMENT '优惠金额',
  `money` int(11) DEFAULT NULL COMMENT '付款金额',
  `created_time` datetime DEFAULT NULL COMMENT '创建订单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `item_id` int(11) DEFAULT NULL COMMENT '商品id',
  `item_num` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单和商品关系表';

-- ----------------------------
-- Records of t_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for `t_paybag`
-- ----------------------------
DROP TABLE IF EXISTS `t_paybag`;
CREATE TABLE `t_paybag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `money` int(11) DEFAULT NULL COMMENT '余额',
  `u_money` int(11) DEFAULT NULL COMMENT '优豆',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='钱包表';

-- ----------------------------
-- Records of t_paybag
-- ----------------------------

-- ----------------------------
-- Table structure for `t_paybag_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `t_paybag_coupon`;
CREATE TABLE `t_paybag_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `paybag_id` int(11) DEFAULT NULL COMMENT '钱包id',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='钱包和优惠券关系表';

-- ----------------------------
-- Records of t_paybag_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关系表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_name` varchar(20) DEFAULT NULL COMMENT '类型名',
  `type_img` varchar(20) DEFAULT NULL COMMENT '类型图片',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类型id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品类型表';

-- ----------------------------
-- Records of t_type
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `username` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `user_img` varchar(100) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_address`;
CREATE TABLE `t_user_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `address_id` int(11) DEFAULT NULL COMMENT '地址id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和地址关系表';

-- ----------------------------
-- Records of t_user_address
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_item`;
CREATE TABLE `t_user_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL COMMENT '商品id',
  `item_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品和用户关系表(购物车表)';

-- ----------------------------
-- Records of t_user_item
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_order`;
CREATE TABLE `t_user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和订单关系表';

-- ----------------------------
-- Records of t_user_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_paybag`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_paybag`;
CREATE TABLE `t_user_paybag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `paybag_id` int(11) DEFAULT NULL COMMENT '钱包id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和钱包关系表';

-- ----------------------------
-- Records of t_user_paybag
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
