<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="/MavenSpringMVC/static/css/springDemo.css" />
<script type="text/javascript" src="/MavenSpringMVC/static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/MavenSpringMVC/static/js/springDemo.js"></script>
</head>
<body>
	<form action="login/test" method="post" id="form">
		<div class="main">
			账号:<input type="text" name="username" /><br /> 
			密码:<input type="text" name="password" /><br /> 
			<input type="submit" value="Login"/>
		</div>
	</form>
</body>
</html>
