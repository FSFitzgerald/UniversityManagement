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

public class ModuleDAOImpl implements ModuleDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	@Override
	public List<Module> getModule(int year, int semester) {
		List<Module> moduleList = new ArrayList<>();
		
		try {
			connection = DBConnectionUtil.openConnection();
			String sql = "select sj.subject_id, sj.subject_name, sj.credits, tch.teacher_name, tch.teacher_id from module as m\r\n"
					+ "join subject as sj on m.subject = sj.subject_id\r\n"
					+ "join teacher as tch on m.teacher = tch.teacher_id\r\n"
					+ "where year = ? and semester = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, semester);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Module module = new Module();
				module.setId(resultSet.getString("subject_id"));
				module.setName(resultSet.getString("subject_name"));
				module.setCredits(resultSet.getInt("credits"));
				module.setTeacher(resultSet.getString("teacher_name"));
				module.setTeacher_id(resultSet.getString("teacher_id"));
				moduleList.add(module);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return moduleList;
	}

}
