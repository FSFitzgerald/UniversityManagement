use university;

select st.student_name, sum(sb.credits) from student st join module_register mr on st.student_id = mr.student
join module m on m.module_id = mr.module
join subject sb on sb.subject_id = m.subject
where m.year = 2020;

select * from module_register;
select * from student;
select * from module;
select * from subject;
select * from teacher;

delete from module_register where student = 'SV001';

select module_id from module where subject = 'NMLT' and year = 2020 and semester = 1 and teacher = 'GV001';

select sj.subject_id, sj.subject_name, sj.credits, tch.teacher_name, tch.teacher_id from module as m
join subject as sj on m.subject = sj.subject_id
join teacher as tch on m.teacher = tch.teacher_id
where year = 2020 and semester = 1;

select s.subject_id, s.subject_name, s.credits, tch.teacher_name, mr.grade_point from module_register as mr 
join module as m on mr.module = m.module_id
join subject as s on m.subject = s.subject_id
join student as st on st.student_id = mr.student
join teacher tch on m.teacher = tch.teacher_id
where mr.student = 'SV001' and m.year = 2020 and m.semester = 1;

select * from module m join subject s on m.subject = s.subject_id;



select * from module where year = 2020 and semester = 1;

update student set student_name = N'Trần Minh DDD',  dob = '2000-1-1', gender = 1 where student_id = 'SV001';