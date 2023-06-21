CREATE DATABASE STUDENT_MANAGEMENT
USE STUDENT_MANAGEMENT

CREATE TABLE user (
    user_id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_name varchar(20) NOT NULL,
    password varchar(15) NOT NULL
)

CREATE TABLE student (
    student_id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    student_name varchar(20) NOT NULL,
    student_code varchar(10) NOT NULL
)

CREATE TABLE student_info (
    info_id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    student_id int,
    address varchar(255),
    average_score double,
    date_of_birth date
)

ALTER TABLE student_info
ADD FOREIGN KEY (student_id) REFERENCES student(student_id);