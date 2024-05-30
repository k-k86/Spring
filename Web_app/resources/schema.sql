DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS admin_user;
DROP TYPE IF EXISTS role;

create table employee (
	employee_id serial PRIMARY KEY NOT NULL,
	employee_name varchar(100) NOT NULL,
	employee_name_kana varchar(150) NOT NULL,
	employee_age Integer,
	employee_address varchar(255),
	employee_mail varchar(255),
	employee_phone varchar(30),
	employee_hiredate date,
	department_id char(3) NOT NULL
);

create table department (
	department_id char(3) primary key ,
	department_name varchar(30) unique,
	department_extension varchar(30)
);


ALTER TABLE employee ADD FOREIGN KEY (department_id) REFERENCES department(department_id);

CREATE TYPE role AS ENUM ('ADMIN', 'USER');

CREATE TABLE admin_user (
	user_id varchar(30) PRIMARY KEY,
	user_password varchar(255) NOT NULL,
	admin_level role NOT NULL
	);


