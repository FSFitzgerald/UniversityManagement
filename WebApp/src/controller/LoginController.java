package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;
import dao.LoginDAOImpl;
import entity.Login;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Login login = new Login();
		LoginDAO loginDAO = new LoginDAOImpl();
		login.setId(req.getParameter("id"));
		login.setPassword(req.getParameter("password"));
		String statusLogin = loginDAO.authenticate(login);
		HttpSession session = req.getSession();
		if(statusLogin.equals("true")) {
			session.setAttribute("id", login.getId());
			resp.sendRedirect("StudentController?action=Info");
		}else if(statusLogin.equals("false")){
			resp.sendRedirect("index.jsp?status=false");
		}else {
			resp.sendRedirect("index.jsp?status=error");
		}
	}

}
