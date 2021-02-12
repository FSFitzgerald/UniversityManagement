package dao;

import entity.Login;

public interface LoginDAO {
	String authenticate(Login login);	
}
