<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change information student</title>
</head>
<link href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<body>
	<div class="container">
		<h1>Change student information</h1>
		<br/>
		<div class="row">
			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/StudentController/ChangeInfo" accept-charset="utf-8" method="post" >
					<div class="form-group">
						<input type="text" name="id" value="${student.id}" placeholder="Enter id" class="form-control"/><br/>
					</div>
					<div class="form-group">
						<input type="text" name="name" value="${student.name}" placeholder="Enter name" class="form-control"/><br/>
					</div>
					<div class="form-group">
						<input type="text" name="gender" value="${student.gender eq true ? "Nam" : "Nữ"}" placeholder="Enter gender" class="form-control"/><br/>
					</div>
					<div class="form-group">
						<input type="date" name="dob" value="${student.dob}" placeholder="Enter dob" class="form-control"/><br/>
					</div>
					<div class="form-group">
						<input type="text" name="faculty" value="${student.faculty}" placeholder="Enter faculty" class="form-control"/><br/>
					</div>
					<input type="submit" name="update" value="Update" class="btn btn-primary"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>