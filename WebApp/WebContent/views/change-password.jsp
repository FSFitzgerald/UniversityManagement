<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change password</title>
</head>
<link href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<body>

	<%
		String status = (String)request.getAttribute("changeStatus");
		if(status != null){
			out.println(status);
		}
	%>

	<div class="container">
			<form action="${pageContext.request.contextPath}/StudentController/ChangePassword" method="POST">
				<div class="card">
				<div class="card-header">
					Change password
				</div>
				<div class="card-body">
					<div class="form-group">
						<input required type="password" name="old-password" class="form-control" placeholder="Enter old password"/>
					</div>
					<div class="form-group">
						<input required type="password" name="new-password" class="form-control" placeholder="Enter new password"/>
					</div>
					<div class="form-group">
						<input required type="password" name="re-password" class="form-control" placeholder="Enter password again"/>
					</div>			
				</div>
				<div class="card-footer">
					<input type="submit" name="change" value="Change" class="btn btn-primary"/> 
				</div>
				</div>
			</form>
	</div>
</body>
</html>