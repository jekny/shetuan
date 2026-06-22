/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : shetuan

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 22/06/2026 18:33:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `club_id` bigint NOT NULL COMMENT '所属社团id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动标题',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动地点',
  `max_num` int NOT NULL COMMENT '最大报名人数',
  `enroll_end_time` datetime NOT NULL COMMENT '报名截止时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图文详情',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PUBLISH' COMMENT '状态 PUBLISH正常 CANCEL取消 END已结束',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, 1, '校内3v3篮球对抗赛', '2026-06-25 14:00:00', '2026-06-25 17:30:00', '一号篮球场', 30, '2026-06-24 22:00:00', '全校学生均可报名，组队参赛，设有冠亚季军奖品', 'PUBLISH', '2026-06-22 17:40:38');
INSERT INTO `activity` VALUES (2, 1, '篮球基础训练课', '2026-06-28 16:00:00', '2026-06-28 18:00:00', '一号篮球场', 20, '2026-06-27 20:00:00', '零基础篮球教学，无需基础，社团免费提供篮球', 'PUBLISH', '2026-06-22 17:40:38');
INSERT INTO `activity` VALUES (3, 2, '夏日动漫茶话会', '2026-06-26 10:00:00', '2026-06-26 16:00:00', '图书馆三楼活动室', 40, '2026-06-25 12:00:00', '动漫分享、手绘交换、cosplay自由交流，提供饮品小零食', 'CANCEL', '2026-06-22 17:40:38');

-- ----------------------------
-- Table structure for activity_comment
-- ----------------------------
DROP TABLE IF EXISTS `activity_comment`;
CREATE TABLE `activity_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论文字',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父评论id 0为一级评论',
  `top` tinyint NULL DEFAULT 0 COMMENT '0不置顶 1置顶',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_comment
-- ----------------------------
INSERT INTO `activity_comment` VALUES (1, 1, 1, '活动很棒，期待比赛！', '', 0, 1, '2026-06-22 17:40:38');
INSERT INTO `activity_comment` VALUES (2, 1, 2, '场地环境很好，已经组队完毕', '', 0, 0, '2026-06-22 17:40:38');
INSERT INTO `activity_comment` VALUES (3, 1, 3, '欢迎大家踊跃参赛，奖品丰厚', '', 0, 0, '2026-06-22 17:40:38');
INSERT INTO `activity_comment` VALUES (4, 2, 1, 'hello', NULL, 0, 0, '2026-06-22 17:48:10');
INSERT INTO `activity_comment` VALUES (5, 2, 5, '我要报名', NULL, 0, 0, '2026-06-22 18:19:34');

-- ----------------------------
-- Table structure for activity_enroll
-- ----------------------------
DROP TABLE IF EXISTS `activity_enroll`;
CREATE TABLE `activity_enroll`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` bigint NOT NULL COMMENT '活动id',
  `user_id` bigint NOT NULL COMMENT '学生id',
  `enroll_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_enroll
-- ----------------------------
INSERT INTO `activity_enroll` VALUES (1, 1, 1, '2026-06-22 17:40:38');
INSERT INTO `activity_enroll` VALUES (2, 1, 2, '2026-06-22 17:40:38');
INSERT INTO `activity_enroll` VALUES (4, 2, 1, '2026-06-22 17:49:39');
INSERT INTO `activity_enroll` VALUES (5, 2, 5, '2026-06-22 18:18:19');
INSERT INTO `activity_enroll` VALUES (6, 1, 5, '2026-06-22 18:18:43');

-- ----------------------------
-- Table structure for activity_like
-- ----------------------------
DROP TABLE IF EXISTS `activity_like`;
CREATE TABLE `activity_like`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_like
-- ----------------------------
INSERT INTO `activity_like` VALUES (1, 1, 1);
INSERT INTO `activity_like` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for activity_review
-- ----------------------------
DROP TABLE IF EXISTS `activity_review`;
CREATE TABLE `activity_review`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` bigint NOT NULL COMMENT '一个活动一条回顾',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '活动总结',
  `photos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '多张图片逗号分隔',
  `like_num` int NULL DEFAULT 0 COMMENT '点赞数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `activity_id`(`activity_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_review
-- ----------------------------
INSERT INTO `activity_review` VALUES (1, 1, '本次3v3篮球赛共有28名同学参与，比赛氛围热烈，最终机械学院队伍拿下冠军，后续会增加更多小型赛事', 'img1.jpg,img2.jpg', 14, '2026-06-22 17:40:38');

-- ----------------------------
-- Table structure for activity_sign
-- ----------------------------
DROP TABLE IF EXISTS `activity_sign`;
CREATE TABLE `activity_sign`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activity_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `sign_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_sign
-- ----------------------------
INSERT INTO `activity_sign` VALUES (1, 1, 1, '2026-06-22 17:40:38');
INSERT INTO `activity_sign` VALUES (2, 1, 2, '2026-06-22 17:40:38');

-- ----------------------------
-- Table structure for sys_club
-- ----------------------------
DROP TABLE IF EXISTS `sys_club`;
CREATE TABLE `sys_club`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `club_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团名称',
  `leader_id` bigint NOT NULL COMMENT '负责人用户id',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团简介',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_club
-- ----------------------------
INSERT INTO `sys_club` VALUES (1, '篮球社团', 3, '校内体育运动社团，定期举办篮球赛事、日常训练活动', '2026-06-22 17:40:38');
INSERT INTO `sys_club` VALUES (2, '动漫社团', 3, '二次元交流社团，漫展、绘画、剧本分享活动', '2026-06-22 17:40:38');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号/工号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密密码',
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实姓名',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色 STUDENT学生 LEADER社团负责人 ADMIN管理员',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2026001', '1234', '张三', 'STUDENT', '2026-06-22 17:40:38');
INSERT INTO `sys_user` VALUES (2, '2026002', '1234', '李四', 'STUDENT', '2026-06-22 17:40:38');
INSERT INTO `sys_user` VALUES (3, 'leader01', '1234', '王部长', 'LEADER', '2026-06-22 17:40:38');
INSERT INTO `sys_user` VALUES (4, 'admin01', '1234', '系统管理员', 'ADMIN', '2026-06-22 17:40:38');
INSERT INTO `sys_user` VALUES (5, '2351300617', '1234', '小陆', 'STUDENT', '2026-06-22 17:50:15');

SET FOREIGN_KEY_CHECKS = 1;
