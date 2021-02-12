create database university;
use university;

create table faculty(
	faculty_id varchar(10),
    faculty_name nvarchar(30),
    primary key(faculty_id)
);

create table student(
	student_id varchar(10),
    student_name nvarchar(30),
    gender bit, #1: male, 0: female
    dob date,
    faculty varchar(10),
	primary key(student_id),
    constraint student_faculty foreign key(faculty) references faculty(faculty_id)
);

alter table student
add column password varchar(20);

create table teacher(
    teacher_id varchar(10),
    teacher_name nvarchar(30),
    gender bit,
    dob date,
    faculty varchar(10),
	primary key(teacher_id),
    constraint teacher_faculty foreign key(faculty) references faculty(faculty_id)
);

alter table teacher
add column password varchar(20);

create table manager(
    manager_id varchar(10),
    manager_name nvarchar(30),
    gender bit,
    dob date,
	primary key(manager_id)
);

alter table manager
add column password varchar(20);

create table subject(
	subject_id varchar(10),
    subject_name nvarchar(30),
    credits int,
    primary key(subject_id)
);

create table module(
	module_id int auto_increment,
	subject varchar(10),
    year int,
    semester int,
    teacher varchar(10),
    primary key(module_id),
    constraint module_subject foreign key(subject) references subject(subject_id),
    constraint module_teacher foreign key(teacher) references teacher(teacher_id)
);

create table module_register(
	module int,
    student varchar(10),
    grade_point float,
    primary key(module, student),
    constraint register_module foreign key(module) references module(module_id),
    constraint register_student foreign key(student) references student(student_id)
);








