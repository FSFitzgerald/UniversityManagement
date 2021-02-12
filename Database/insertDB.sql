use university;

select * from faculty;
insert into faculty
values('CNTT', N'Công nghệ thông tin'),
('CNSH', N'Công nghệ sinh học');

select * from manager;
insert into manager
values('GV001', N'Nguyễn Văn A', 1, '1980-5-12');

select * from teacher;
insert into teacher
values('GV001', N'Nguyễn Văn B', 1, '1976-2-12', 'CNTT'),
('GV002', N'Nguyễn Thị C', 0, '1971-12-3', 'CNSH');


select * from student;
insert into student
values('SV001', N'Trần Minh D', 1, '2000-5-5', 'CNSH'),
('SV002', N'Hồ Thị E', 0, '2000-3-17', 'CNSH'),
('SV003', N'Phạm Văn F', 1, '2000-8-20', 'CNTT'),
('SV004', N'Lý Văn G', 0, '2000-9-10', 'CNTT');

select * from subject;
insert into subject
values('XSTK', N'Xác suất thống kê', 3),
('TRR', N'Toán rời rạc', 3),
('NMLT', N'Nhập môn lập trình', 4),
('KTLT', N'Kỹ thuật lập trình', 4),
('NMCNSH', N'Nhập môn công nghệ sinh học', 4),
('SHTB', N'Sinh học tế bào', 4);

select * from module;
insert into module(subject, year, semester, teacher)
values('NMLT', 2020, 1, 'GV001'),
('SHTB', 2020, 1, 'GV002');

select * from module_register;
insert into module_register(module, student)
values(1, 'SV003'),
(1, 'SV004'),
(2, 'SV001'),
(2, 'SV002');




