package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Login;
import util.DBConnectionUtil;

public class LoginDAOImpl implements LoginDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public String authenticate(Login login) {
		try {
			connection = DBConnectionUtil.openConnection();
			String sql = "select * from student where student_id = ? and password = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getId());
			preparedStatement.setString(2, login.getPassword());
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return "true";
			}else {
				return "false";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return "error";
	}

}
