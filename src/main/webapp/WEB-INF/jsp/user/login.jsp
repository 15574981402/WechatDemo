<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>用户注册</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js">
		</script>
	</head>

	<body>
		<div class="container">

			<form id="loginForm" class="form-signin" action="${pageContext.request.contextPath}/user/login.action" method="post">
				<h2 class="form-signin-heading">用户注册</h2>
				<div id="msg"></div>
				<div class="login-wrap">
					<input type="hidden" name="openid" id="openid" value="${user.openid}" />
					<input type="text" class="form-control" placeholder="请输入用户名" id="username" name="username" value="${user.username}" maxlength="30" autofocus onfocusout="validataUsername()" onfocus="msgclean()">
					<input type="password" class="form-control" placeholder="请输入密码" name="password" value="${user.password}">
					<input type="password" class="form-control" placeholder="请输入确认密码" value="${user.password}">
					<input type="text" class="form-control" placeholder="请输入姓名" name="name" value="${user.name}">
					<input type="text" class="form-control" placeholder="手机号" pattern="^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$" oninvalid="setCustomValidity('请输入11位正确手机号')" name="phone" value="${user.phone}">
					<select name="unit" class="form-control">
						<option value="请选择公司" selected="selected">请选择公司</option>
						<option value="湖南检修公司">湖南检修公司</option>
						<option value="湖南长沙">湖南长沙</option>
						<option value="湖南衡阳">湖南衡阳</option>
					</select>
					<div>
						<input type="radio" value="1" name="systemtype" checked="checked">数据库系统</input>
						<input type="radio" value="2" name="systemtype">计划系统</input>
					</div>
					<input type="submit" id="submitform" class="form-control" value="注册" onclick="submitform()"/>

				</div>
			</form>
			<script type="text/javascript">
				function msgclean() {
					$("#msg").html("");
				}
//				function submitform() {
//					$("#loginForm").Submit();
//					$("#submitform").
//				}
				function validataUsername() {
					var username = $("#msg").html();
					if(username == "") {
						$("#msg").html("用户名不允许为空");
					} else {
						$.post("${pageContext.request.contextPath}/user/validataUsername.action", {
							"username",
							username
						}, function(result) {
							if("ok" != result) {
								$("#msg").html("用户名已经存在");
							}
						});
					}

				}
			</script>
		</div>
	</body>

</html>