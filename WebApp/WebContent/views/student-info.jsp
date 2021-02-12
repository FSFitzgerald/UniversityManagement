<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student information</title>
</head>
<link href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<body>

	<%
		String id = (String)session.getAttribute("id");
		if(id == null){
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	%>
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<div class="collapse navbar-collapse">
    		<ul class="navbar-nav">
		      <li class="nav-item">
		        <a class="nav-link" href="StudentController?action=Info">Hồ sơ</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="StudentController?action=SubjectInfo">Môn học</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="StudentController?action=ModuleInfo">Học phần mở</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="StudentController?action=ModuleRegister">Đăng ký học phần</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="StudentController?action=HistoryModule">Lịch sử đăng ký học phần</a>
		      </li>
    		</ul>
 		</div>
	</nav>
	
	<div class="container">
		<h4>Student information</h4>
		<br/>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
						<input type="text" name="id" value="${student.id}" class="form-control"/><br/>
				</div>
				<div class="form-group">
						<input type="text" name="name" value="${student.name}" class="form-control"/><br/>
				</div>
				<div class="form-group">
						<input type="text" name="gender" value="${student.gender eq true ? "Nam" : "Nữ"}" class="form-control"/><br/>
				</div>
				<div class="form-group">
						<input type="date" name="dob" value="${student.dob}" class="form-control"/><br/>
				</div>
				<div class="form-group">
						<input type="text" name="faculty" value="${student.faculty}" class="form-control"/><br/>
				</div>
				<div class="float-right">
					<a href="StudentController?action=UpdateInfo">Thay đổi thông tin</a>
					|
					<a href="StudentController?action=ChangePassword">Đổi mật khẩu</a>
					|
					<a href="${pageContext.request.contextPath}/logout.jsp">Thoát</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>