<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>问题系统展示列表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js">
		</script>
	</head>

	<body>
		<table>
			<thead>
				<tr>
					<th>问题编号</th>
					<th>问题系统</th>
					<th>问题模块</th>
					<th>问题描述</th>
					<th>问题附件</th>
					<th>问题提出时间</th>
					<th>问题状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pList}" var="problem">
					<tr>
						<td>${problem.pid}</td>
						<td>${problem.systemname}</td>
						<td>${problem.modulename}</td>
						<td>${problem.pdesc}</td>
						<c:if test="${empty problem.purl}">
							<td></td>
						</c:if>
						<c:if test="${not empty problem.purl}">
							<td><input type="button" name="查看附件" onclick="#" /></td>
						</c:if>

						<td>${problem.pusertime}</td>
						<td>${problem.pstate}</td>
					</tr>
				</c:forEach>

			</tbody>

		</table>
	</body>

</html>