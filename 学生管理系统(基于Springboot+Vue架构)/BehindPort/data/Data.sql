-- 学生管理系统
CREATE DATABASE StudentSystem;

USE StudentSystem;

-- 班级表
CREATE TABLE class
(
    ID         INT PRIMARY KEY AUTO_INCREMENT, -- 编号
    Class_Name VARCHAR(1024) NOT NULL          -- 班级名称
);

-- 学生表
CREATE TABLE student
(
    ID              INT PRIMARY KEY AUTO_INCREMENT, -- 编号
    Student_Name    VARCHAR(1024) NOT NULL,         -- 学生姓名
    Student_Age     VARCHAR(1024) NOT NULL,         -- 学生年龄
    Student_Sex     VARCHAR(512)  NOT NULL,         -- 学生性别
    Student_Address VARCHAR(2048) NOT NULL,         -- 学生居住地
    Class_ID        INT,                            -- 班级编号
    CONSTRAINT class_student_fk FOREIGN KEY (class_ID) REFERENCES class (ID)
);

-- 测试数据
INSERT INTO `class`
VALUES (1, '计算机01');
INSERT INTO `class`
VALUES (2, '计算机02');
INSERT INTO `class`
VALUES (3, '计算机03');
INSERT INTO `class`
VALUES (4, '护理01');
INSERT INTO `class`
VALUES (5, '护理02');

INSERT INTO `student`
VALUES (1, '小红', '19', '女', '北京', 4);
INSERT INTO `student`
VALUES (2, '小明', '20', '男', '上海', 1);