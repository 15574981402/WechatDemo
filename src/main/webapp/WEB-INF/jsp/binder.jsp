<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>绑定用户</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<style>
.roundbtn {
	width: 40px;
	height: 40px;
	margin-top: 25px;
	-webkit-border-radius: 20px;
	-moz-border-radius: 20px;
	-ms-border-radius: 20px;
	-o-border-radius: 20px;
	border-radius: 20px;
}

@media all and (min-width: 800px) {
	#nav {
		width: 300px;
		float: left;
		margin-right: 20px;
	}
	#grid {
		width: 450px;
		float: left;
	}
}

@media all and (max-width: 799px) {
	#nav {
		width: 100%;
	}
	#grid {
		width: 100%;
	}
}
</style>
</head>
<body>
	<div data-role="page" id="home" data-theme="a">
		<div data-role="header" data-theme="b">
			<h1>用户绑定</h1>
			<a href="#about" data-rel="dialog" data-role="button" class="roundbtn ui-btn ui-shadow ui-btn-up-c ui-btn-left">
				<div style="margin-top: 10px; font-size: 15px">关于</div>
			</a>
		</div>
		<div data-role="content">
			<form action="/binder.action" method="post">
			<font color="red">${msg}</font><br/>
                               用户名:<input type="text" name="name" value="${employee.name}" required placeholder="请输入用户名" />
                               密码 :<input type="text" name="password"	value="${employee.password}" required placeholder="请输入密码" />
			<input  type="submit" value="绑定"/>
			<a href="/register.action" data-role="button">注册</a>
			</form>
		</div>
		<div data-role="footer" data-position="fixed" data-theme="b">
			<h4>底部</h4>
		</div>
	</div>
	<!-- About Dialog -->
	<div id="about" data-role="page">
		<div data-role="header">
			<h1>关于</h1>
		</div>
	</div>
</body>
</html>