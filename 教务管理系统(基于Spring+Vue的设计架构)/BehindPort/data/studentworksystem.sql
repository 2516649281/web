/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : studentworksystem

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 10/02/2022 18:05:51
*/
CREATE DATABASE studentworksystem;

USE studentworksystem;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`
(
    `Class_Id`   int(0)                                                       NOT NULL AUTO_INCREMENT COMMENT '班级编号',
    `Class_Name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
    PRIMARY KEY (`Class_Id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `Student_Id`      int(0)                                                         NOT NULL AUTO_INCREMENT COMMENT '编号',
    `Student_Name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL COMMENT '学生姓名',
    `Student_Age`     int(0)                                                         NOT NULL COMMENT '学生年龄',
    `Student_Sex`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL COMMENT '学生性别',
    `Student_Address` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生居住地',
    `Class_ID`        int(0)                                                         NULL DEFAULT NULL COMMENT '班级编号',
    `User_ID`         int(0)                                                         NULL DEFAULT NULL COMMENT '用户编号',
    PRIMARY KEY (`Student_Id`) USING BTREE,
    INDEX `Student_Work_fk` (`Class_ID`) USING BTREE,
    INDEX `Student_User_fk` (`User_ID`) USING BTREE,
    CONSTRAINT `Student_User_fk` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_Id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `Student_Work_fk` FOREIGN KEY (`Class_ID`) REFERENCES `class` (`Class_Id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 204
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_work
-- ----------------------------
DROP TABLE IF EXISTS `student_work`;
CREATE TABLE `student_work`
(
    `Student_Work_Id` int(0)                                                         NOT NULL AUTO_INCREMENT COMMENT '编号',
    `Student_ID`      int(0)                                                         NULL DEFAULT NULL COMMENT '学生编号',
    `Work_ID`         int(0)                                                         NULL DEFAULT NULL COMMENT '作业编号',
    `Student_Work`    varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生作业',
    `Student_Source`  double                                                         NULL DEFAULT 0 COMMENT '学生成绩',
    PRIMARY KEY (`Student_Work_Id`) USING BTREE,
    INDEX `Student_fk` (`Student_ID`) USING BTREE,
    INDEX `SWork_fk` (`Work_ID`) USING BTREE,
    CONSTRAINT `Student_fk` FOREIGN KEY (`Student_ID`) REFERENCES `student` (`Student_Id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `SWork_fk` FOREIGN KEY (`Work_ID`) REFERENCES `work` (`Work_Id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 235
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `Teacher_Id`      int(0)                                                         NOT NULL AUTO_INCREMENT COMMENT '编号',
    `Teacher_Name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL COMMENT '教师姓名',
    `Teacher_Age`     int(0)                                                         NOT NULL COMMENT '教师年龄',
    `Teacher_Sex`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL COMMENT '教师性别',
    `Teacher_Address` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师居住地',
    `User_ID`         int(0)                                                         NULL DEFAULT NULL COMMENT '用户编号',
    PRIMARY KEY (`Teacher_Id`) USING BTREE,
    INDEX `Teacher_User_fk` (`User_ID`) USING BTREE,
    CONSTRAINT `Teacher_User_fk` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_Id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 53
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `User_Id`          int(0)                                                       NOT NULL AUTO_INCREMENT COMMENT '编号',
    `User_Name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
    `User_Password`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
    `User_Deleted`     int(0)                                                       NOT NULL DEFAULT 0 COMMENT '冻结指数',
    `User_Create_Time` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `User_Update_Time` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    `User_Identity`    int(0)                                                       NOT NULL DEFAULT 0 COMMENT '身份码',
    `User_Admin`       int(0)                                                       NOT NULL DEFAULT 0 COMMENT '管理员码',
    PRIMARY KEY (`User_Id`) USING BTREE,
    UNIQUE INDEX `username` (`User_Name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 263
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`
(
    `Work_Id`      int(0)                                                         NOT NULL AUTO_INCREMENT COMMENT '编号',
    `Work_Name`    varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作业姓名',
    `Work_Content` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业要求',
    `Teacher_Id`   int(0)                                                         NULL DEFAULT NULL COMMENT '教师编号',
    `Class_Id`     int(0)                                                         NULL DEFAULT NULL COMMENT '班级编号',
    PRIMARY KEY (`Work_Id`) USING BTREE,
    INDEX `Class_Work_fk` (`Class_Id`) USING BTREE,
    INDEX `Teacher_Work_fk` (`Teacher_Id`) USING BTREE,
    CONSTRAINT `Class_Work_fk` FOREIGN KEY (`Class_Id`) REFERENCES `class` (`Class_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `Teacher_Work_fk` FOREIGN KEY (`Teacher_Id`) REFERENCES `teacher` (`Teacher_Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 71
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
