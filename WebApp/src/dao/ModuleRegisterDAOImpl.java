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

public class ModuleRegisterDAOImpl implements ModuleRegisterDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int year;
	int semester;
	
	public ModuleRegisterDAOImpl(int year, int semester) {
		this.year = year;
		this.semester = semester;
	}
	
	@Override
	public boolean register(List<Module> moduleList, String student_id) {
		boolean result = false;
		try {
			connection = DBConnectionUtil.openConnection();
			List<Integer> moduleIdList = new ArrayList<>();
			
			String sql = "select module_id from module where subject = ? and year = ? and semester = ? and teacher = ?;";
			preparedStatement = connection.prepareStatement(sql);
			for(Module module : moduleList) {
				preparedStatement.setString(1, module.getId());
				preparedStatement.setInt(2, this.year);
				preparedStatement.setInt(3, this.semester);
				preparedStatement.setString(4, module.getTeacher_id());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					System.out.println(resultSet.getInt("module_id"));
					moduleIdList.add(resultSet.getInt("module_id"));
				}
			}
	
			sql = "insert into module_register(module, student) values(?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, student_id);
			for(Integer i : moduleIdList) {
				preparedStatement.setInt(1, i);
				preparedStatement.execute();
			}
			
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean delete(List<Module> moduleList, String student_id) {
		boolean result = false;
		try {
			connection = DBConnectionUtil.openConnection();
			List<Integer> moduleIdList = new ArrayList<>();
			
			String sql = "select module_id from module where subject = ? and year = ? and semester = ? and teacher = ?;";
			preparedStatement = connection.prepareStatement(sql);
			for(Module module : moduleList) {
				preparedStatement.setString(1, module.getId());
				preparedStatement.setInt(2, this.year);
				preparedStatement.setInt(3, this.semester);
				preparedStatement.setString(4, module.getTeacher_id());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					System.out.println(resultSet.getInt("module_id"));
					moduleIdList.add(resultSet.getInt("module_id"));
				}
			}
	
			sql = "delete from module_register where module = ? and student = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, student_id);
			for(Integer i : moduleIdList) {
				preparedStatement.setInt(1, i);
				preparedStatement.execute();
			}
			
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
