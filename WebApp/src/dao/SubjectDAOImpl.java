package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Subject;
import util.DBConnectionUtil;

public class SubjectDAOImpl implements SubjectDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	@Override
	public List<Subject> getSubject() {
		List<Subject> list = new ArrayList<>();
		try {
			connection = DBConnectionUtil.openConnection();
			String sql = "select * from subject";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Subject subject = new Subject();
				subject.setId(resultSet.getString("subject_id"));
				subject.setName(resultSet.getString("subject_name"));
				subject.setCredits(resultSet.getInt("credits"));
				list.add(subject);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
