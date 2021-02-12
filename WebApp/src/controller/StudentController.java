package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HistoryModuleDAO;
import dao.HistoryModuleDAOImpl;
import dao.ModuleDAO;
import dao.ModuleDAOImpl;
import dao.ModuleRegisterDAO;
import dao.ModuleRegisterDAOImpl;
import dao.StudentDAO;
import dao.StudentDAOImpl;
import dao.SubjectDAO;
import dao.SubjectDAOImpl;
import entity.Module;
import entity.Student;
import entity.Subject;

public class StudentController extends HttpServlet{
	RequestDispatcher dispatcher = null;
	StudentDAO studentDAO = null;
	SubjectDAO subjectDAO = null;
	ModuleDAO moduleDAO = null;
	HistoryModuleDAO historyModuleDAO = null;
	ModuleRegisterDAO moduleRegisterDAO = null;
	int year;
	int semester;
	
	public StudentController() {
		studentDAO = new StudentDAOImpl();
		subjectDAO = new SubjectDAOImpl();
		moduleDAO = new ModuleDAOImpl();
		historyModuleDAO = new HistoryModuleDAOImpl();
		year = 2020;
		semester = 1;
		moduleRegisterDAO = new ModuleRegisterDAOImpl(year, semester);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action == null) {
			action = "Info";
		}else {
			switch(action) {
			case "Info":
				showInfo(req, resp);
				break;
			case "ChangePassword":
				changePassword(req, resp);
				break;
			case "UpdateInfo":
				getStudent(req, resp);
				break;
			case "SubjectInfo":
				showSubjectInfo(req, resp);
				break;
			case "ModuleInfo":
				showModuleInfo(req, resp);
				break;
			case "HistoryModule":
				showHistoryModule(req, resp);
				break;
			case "ModuleRegister":
				showModuleRegister(req, resp);
				break;
			}
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		if(req.getParameter("change") != null) {
			String oldPassword = req.getParameter("old-password");
			String newPassword = req.getParameter("new-password");
			String rePassword = req.getParameter("re-password");
			HttpSession session = req.getSession();
			String id = (String)session.getAttribute("id");
			Student student = studentDAO.getInfo(id);
			if(oldPassword.equals(student.getPassword()) && newPassword.equals(rePassword)) {
				studentDAO.changePassword(student, newPassword);
				resp.sendRedirect(req.getContextPath() + "/logout.jsp");
			}else {
				req.setAttribute("changeStatus", "Error");
				dispatcher = req.getRequestDispatcher("/views/change-password.jsp");
				dispatcher.forward(req, resp);
			}
		}else if(req.getParameter("update") != null) {
			HttpSession session = req.getSession();
			String id = (String)session.getAttribute("id");
			Student student = studentDAO.getInfo(id);
			student.setName(req.getParameter("name"));
			student.setGender(req.getParameter("gender").equals("Nam") ? true : false);
			student.setDob(req.getParameter("dob"));
			studentDAO.updateStudent(student);
			resp.sendRedirect(req.getContextPath() + "/StudentController?action=Info");
		}else if(req.getParameter("showModule") != null) {
			int year = Integer.parseInt(req.getParameter("year"));
			int semester = Integer.parseInt(req.getParameter("semester"));
			resp.sendRedirect(req.getContextPath() + "/StudentController?action=ModuleInfo&y=" + year + "&s=" + semester);
		}else if(req.getParameter("showModuleHistory") != null) {
			int year = Integer.parseInt(req.getParameter("year"));
			int semester = Integer.parseInt(req.getParameter("semester"));
			resp.sendRedirect(req.getContextPath() + "/StudentController?action=HistoryModule&y=" + year + "&s=" + semester);
		}else if(req.getParameter("register") != null) {
			List<Module> moduleList = moduleDAO.getModule(year, semester);
			HttpSession session = req.getSession();
			String id = (String)session.getAttribute("id");
			List<Module> moduleRegisted = historyModuleDAO.getModuleRegister(id, year, semester);
			
			for(Module module : moduleRegisted) {
				for(int i = 0; i < moduleList.size(); i++) {
					if(module.getId().equals(moduleList.get(i).getId()) && module.getTeacher().equals(moduleList.get(i).getTeacher())) {
						moduleList.remove(i);
					}
				}
			}
			
			List<Module> moduleSubmit = new ArrayList<>();
			for(int i = 0; i < moduleRegisted.size(); i++) {
				if(req.getParameter(Integer.toString(i)) == null) {
					moduleSubmit.add(moduleRegisted.get(i));
				}
			}
			moduleRegisterDAO.delete(moduleSubmit, id);
			moduleSubmit.clear();
			
			for(int i = moduleRegisted.size(); i < moduleList.size() + moduleRegisted.size(); i++) {
				if(req.getParameter(Integer.toString(i)) != null) {
					moduleSubmit.add(moduleList.get(i - moduleRegisted.size()));
				}
			}
			moduleRegisterDAO.register(moduleSubmit, id);
			resp.sendRedirect(req.getContextPath() + "/StudentController?action=ModuleRegister");
		}
	}
	
	public void showInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		Student student = studentDAO.getInfo(id);
		req.setAttribute("student", student);
		dispatcher = req.getRequestDispatcher("/views/student-info.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void showSubjectInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Subject> subjectList = subjectDAO.getSubject();
		req.setAttribute("subjectList", subjectList);
		dispatcher = req.getRequestDispatcher("/views/subject-info.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void showModuleInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("y") != null && req.getParameter("s") != null) {
			HttpSession session = req.getSession();
			String id = (String)session.getAttribute("id");
			int year = Integer.parseInt(req.getParameter("y"));
			int semester = Integer.parseInt(req.getParameter("s"));
			List<Module> moduleList = moduleDAO.getModule(year, semester);
			req.setAttribute("moduleList", moduleList);
		}
		dispatcher = req.getRequestDispatcher("/views/module-info.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void showHistoryModule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("y") != null && req.getParameter("s") != null) {
			HttpSession session = req.getSession();
			String id = (String)session.getAttribute("id");
			int year = Integer.parseInt(req.getParameter("y"));
			int semester = Integer.parseInt(req.getParameter("s"));
			List<Module> historyModuleList = historyModuleDAO.getModuleRegister(id, year, semester);
			req.setAttribute("historyModuleList", historyModuleList);
		}
		dispatcher = req.getRequestDispatcher("/views/history-module.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void showModuleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Module> moduleList = moduleDAO.getModule(year, semester);
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		List<Module> moduleRegisted = historyModuleDAO.getModuleRegister(id, year, semester);
		for(Module module : moduleRegisted) {
			for(int i = 0; i < moduleList.size(); i++) {
				if(module.getId().equals(moduleList.get(i).getId()) && module.getTeacher().equals(moduleList.get(i).getTeacher())) {
					moduleList.remove(i);
				}
			}
		}
		req.setAttribute("moduleRegisted", moduleRegisted);
		req.setAttribute("moduleList", moduleList);
		dispatcher = req.getRequestDispatcher("/views/module-register.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatcher = req.getRequestDispatcher("/views/change-password.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void getStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		Student student = studentDAO.getInfo(id);
		req.setAttribute("student", student);
		dispatcher = req.getRequestDispatcher("/views/student-change-info.jsp");
		dispatcher.forward(req, resp);
	}
}
