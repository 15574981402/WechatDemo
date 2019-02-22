<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>用户注册</title>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
</head>
<body>
	<div data-role="page" id="home">
		<div data-role="header">
			<a href="" data-rel="back" data-icon="back">返回</a>
			<h1>用户注册</h1>
		</div>
		<div data-role="content">
			<form action="/register" method="post">
			<font color="red">${msg}</font><br/>
                           用户名:<input type="text" name="name" value="${employee.name}" required placeholder="请输入用户名" />
                          密码 :<input type="text" name="password"	value="${employee.password}" required placeholder="请输入密码" />
				<input type="submit" value="注册" />
			</form>
		</div>
		<div data-role="footer" data-position="fixed">
			<h4>底部</h4>
		</div>
	</div>
</body>
</html>