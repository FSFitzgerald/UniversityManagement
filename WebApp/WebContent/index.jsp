<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
</head>
<link href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel="stylesheet" />
<body>
	<%
		
			String id = (String)session.getAttribute("id");
			//if user already logged in redirect it to list employee
			if(id != null){
				response.sendRedirect("StudentController?action=Info");
			}
		
			String status = request.getParameter("status");
			if(status != null){
				if(status.equals("false")){
					out.println("Login fail");
				}else if(status.equals("error")){
					out.println("Some error occured");
				}
			}
		%>
	<div class="container">
			<form action="LoginProcess" method="POST">
				<div class="card">
				<div class="card-header">
					Login
				</div>
				<div class="card-body">
					<div class="form-group">
						<input type="text" name="id" class="form-control" placeholder="Enter id"/>
					</div>
					<div class="form-group">
						<input type="password" name="password" class="form-control" placeholder="Enter password"/>
					</div>				
				</div>
				<div class="card-footer">
					<input type="submit" value="Login" class="btn btn-primary"/> 
				</div>
				</div>
			</form>
		</div>
</body>
</html>