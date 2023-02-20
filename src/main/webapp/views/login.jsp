<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container">
		<h1 class="form-heading">login Form</h1> 
		<div class="login-form">
			<div class="main-div">
				<c:if test="${not empty message}">
					<div class="alert alert-${alert}">
	    				<strong>${alert}</strong><br> 
	    				${message}
	  				</div>
				</c:if>
	  			<!-- <div class="alert alert-danger">
	    			<strong>Danger!</strong> Login fail.
	  			</div> -->
				<div class="panel">
					<h2>Admin Login</h2>
					<p>Please enter your username and password</p>
				</div>
				<form action= "<c:url value='/dang-nhap'/>" id="Login" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="userName" name="userName"
							placeholder="Tên Đăng Nhập">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="Password">
					</div>
					<div class="forgot">
						<a href="reset.html">Forgot password?</a>
					</div>
					<input type="hidden" value="login" id= "action" name= "action"/> 
					<button type="submit" class="btn btn-primary">Đăng Nhập</button>
				</form>
			</div>
			<p class="botto-text">Designed by Văn Dũng</p> 
		</div>
	</div>
</body>
</html>