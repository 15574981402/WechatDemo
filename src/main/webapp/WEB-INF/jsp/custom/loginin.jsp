<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>客服登录界面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js">
		</script>
	</head>

	<body>
		<div class="container">

			<form class="form-signin" action="${pageContext.request.contextPath}/custom/loginin.action" method="post">
				<h2 class="form-signin-heading">客服登录</h2>
				<div>${msg}</div>
				<span class="pull-right"> <a href="#">忘记密码</a></span>
				<div class="login-wrap">
					<input type="text" class="form-control" placeholder="请输入用户名" id="cname" name="cname" autofocus>
					<input type="password" class="form-control" placeholder="请输入密码"id="cpassword"  name="cpassword">
					<input type="submit" class="form-control" value="登录" />
				</div>
			</form>
		</div>
	</body>

</html>