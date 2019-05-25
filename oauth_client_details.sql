/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : springsecurity

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 25/05/2019 15:22:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('booyue', 'root', '$2a$10$8ZHQwm5gRwcoD417jDaCwOqfplvabRiDmvLsGLYv0C0QQWvPCtzyC', 'ui', 'client_credentials', NULL, 'ROLE_READ', 600, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('huohuotu', 'root', '$2a$10$8ZHQwm5gRwcoD417jDaCwOqfplvabRiDmvLsGLYv0C0QQWvPCtzyC', 'ui', 'client_credentials', NULL, 'ROLE_READ', 600, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('service-hi', 'root', '$2a$10$8ZHQwm5gRwcoD417jDaCwOqfplvabRiDmvLsGLYv0C0QQWvPCtzyC', 'ui', 'client_credentials', NULL, 'ROLE_ADMIN', 600, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
