<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>History module register</title>
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
	
		<h4>History module</h4>
	
		<form action="${pageContext.request.contextPath}/StudentController/HistoryModule" method="POST">
		  <div>
		    <input required type="text" name="year" placeholder="Enter year"/>
		    <input required type="text" name="semester" placeholder="Enter semester"/>
		    <input type="submit" name="showModuleHistory" value="Xem" class="btn btn-primary"/>
		  </div>
		</form>
		
		<br/>
	
		<table border = "1" class="table table-striped table-bordered" id="datatable">
			<thead>
				<tr class="thead-dark">
					<th>Mã môn học</th>
					<th>Tên môn học</th>
					<th>Số tín chỉ</th>
					<th>Giáo viên phụ trách</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items = "${historyModuleList}" var = "historyModule">
					<tr>
						<td>${historyModule.id}</td>
						<td>${historyModule.name}</td>
						<td>${historyModule.credits}</td>
						<td>${historyModule.teacher}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>