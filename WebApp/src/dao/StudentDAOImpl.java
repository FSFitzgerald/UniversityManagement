package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import entity.Student;
import util.DBConnectionUtil;

public class StudentDAOImpl implements StudentDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	@Override
	public Student getInfo(String id) {
		Student student = new Student();
		try {
			connection = DBConnectionUtil.openConnection();
			String sql = "select * from student where student_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				student.setId(resultSet.getString("student_id"));
				student.setName(resultSet.getString("student_name"));
				student.setGender(resultSet.getBoolean("gender"));
				student.setFaculty(resultSet.getString("faculty"));
				student.setDob(resultSet.getString("dob"));
				student.setPassword(resultSet.getString("password"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}

	@Override
	public boolean changePassword(Student student, String newPassword) {
		int result = 0;
		try {
			connection = DBConnectionUtil.openConnection();
			String sql = "update student set password = ? where student_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, student.getId());
			result  = preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result == 1 ? true : false;
	}

	@Override
	public boolean updateStudent(Student student) {
		int result = 0;
		try {
			connection = DBConnectionUtil.openConnection();
			String sql = "update student set student_name = N?, gender = ?, dob = ? where student_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setBoolean(2, student.isGender());
			preparedStatement.setString(3, student.getDob());
			preparedStatement.setString(4, student.getId());
			result = preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result == 0 ? false : true;
	}

}
