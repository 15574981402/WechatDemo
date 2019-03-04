<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js">
		</script>
	</head>

	<body>
		<div class="container">

			<form class="form-signin" action="${pageContext.request.contextPath}/user/binder.action" method="post">
				<h2 class="form-signin-heading">用户登录</h2>
				
				<span class="pull-right"> <a href="#">忘记密码</a></span> 
				<div>${user.msg}</div>	
				<input type="hidden" id="openid" name="openid" value="${user.openid}" />

				<div class="login-wrap">
					<div><input type="text" name="username" value="${user.username}" class="form-control" placeholder="请输入用户名" autofocus /> </div>
					<div><input type="password" name="password" value="${user.password}" class="form-control" placeholder="请输入密码" /></div>
					<div><input type="submit" class="form-control" value="绑定" /></div>
					<a href="${pageContext.request.contextPath}/user/login.action?openid=${user.openid}" class="form-control" >用户注册</a>
				</div>
			</form>
		</div>
	</body>

</html>