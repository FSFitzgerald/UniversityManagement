<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Module register</title>
</head>
<link href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<body>
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
		<form action="${pageContext.request.contextPath}/StudentController/ModuleRegister" method="POST">
			<h4>Module register</h4>
	
			<br/>
		
			<table border = "1" class="table table-striped table-bordered" id="datatable">
				<thead>
					<tr class="thead-dark">
						<th>Mã môn học</th>
						<th>Tên môn học</th>
						<th>Số tín chỉ</th>
						<th>Tên giáo viên</th>
						<th>Đăng ký</th>
					</tr>
				</thead>
				<tbody>
					<%int i = 0;%>
					<c:forEach items = "${moduleRegisted}" var = "module">
						<tr>
							<td>${module.id}</td>
							<td>${module.name}</td>
							<td>${module.credits}</td>
							<td>${module.teacher}</td>
							<td><input type="checkbox" name="<%= i%>" Checked/></td>
							<%i++; %>
						</tr>	
					</c:forEach>
					
					<c:forEach items = "${moduleList}" var = "module">
						<tr>
							<td>${module.id}</td>
							<td>${module.name}</td>
							<td>${module.credits}</td>
							<td>${module.teacher}</td>
							<td><input type="checkbox" name="<%= i%>"/></td>
							<%i++; %>
						</tr>	
					</c:forEach>
				</tbody>
			</table>
			
			<input type="submit" name="register" value="Đăng ký" class="btn btn-primary"/>
		</form>
	</div>
</body>
</html>