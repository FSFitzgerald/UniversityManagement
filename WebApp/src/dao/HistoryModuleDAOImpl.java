package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Module;
import util.DBConnectionUtil;

public class HistoryModuleDAOImpl implements HistoryModuleDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	@Override
	public List<Module> getModuleRegister(String id, int year, int semester) {
		List<Module> moduleRegisterList = new ArrayList<>();
		try {
			connection = DBConnectionUtil.openConnection();
			String sql = "select s.subject_id, s.subject_name, s.credits, tch.teacher_name, tch.teacher_id from module_register as mr \r\n"
					+ "join module as m on mr.module = m.module_id\r\n"
					+ "join subject as s on m.subject = s.subject_id\r\n"
					+ "join student as st on st.student_id = mr.student\r\n"
					+ "join teacher tch on m.teacher = tch.teacher_id\r\n"
					+ "where mr.student = ? and m.year = ? and m.semester = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setInt(2, year);
			preparedStatement.setInt(3, semester);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Module moduleRegister = new Module();
				moduleRegister.setId(resultSet.getString("subject_id"));
				moduleRegister.setName(resultSet.getString("subject_name"));
				moduleRegister.setCredits(resultSet.getInt("credits"));
				moduleRegister.setTeacher(resultSet.getString("teacher_name"));
				moduleRegister.setTeacher_id(resultSet.getString("teacher_id"));
				moduleRegisterList.add(moduleRegister);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return moduleRegisterList;
	}

}
