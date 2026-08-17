CREATE DATABASE student_management;

USE student_management;

CREATE TABLE users
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(100) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    role VARCHAR(20) NOT NULL
);

CREATE TABLE students
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(100) UNIQUE,

    phone VARCHAR(10),

    department VARCHAR(100),

    year INT,

    cgpa DOUBLE,

    address VARCHAR(255)
);