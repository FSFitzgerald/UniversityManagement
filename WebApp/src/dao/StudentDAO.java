package dao;

import entity.Login;
import entity.Student;

public interface StudentDAO {
	Student getInfo(String id);
	boolean changePassword(Student student, String newPassword);
	boolean updateStudent(Student student);
}
