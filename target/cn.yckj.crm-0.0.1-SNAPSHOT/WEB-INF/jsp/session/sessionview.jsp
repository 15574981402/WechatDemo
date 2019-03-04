<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<!-- head 中 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/we/weui.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/we/jquery-weui.min.css">

		<!-- body 最后 -->
		<script src="${pageContext.request.contextPath}/js/we/jquery-2.1.4.js"></script>
		<script src="${pageContext.request.contextPath}/js/we/jquery-weui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/we/jquery.form.js">
		</script>

		<script type="text/javascript">
			function sessionviewaction(sid) {
				$.post("${pageContext.request.contextPath}/sessionview/list.action", {
					'sid': sid
				}, function(result) {

					var vlist = JSON.parse(result);
					alert(vlist);
					$("#viewList").html("");
					var s = "";
					$.each(vlist, function(i, item) {
						if("receive" == item.aremake) {
							s += "<div align='left' style='border: 1px solid #adcd3c; background: #f2fddb'>"
						} else {
							s += "<div align='right' style='border: 1px solid #adcd3c; background: #e8f5fe'>"
						}
						s += item.stime;
						if("text" == item.msgtype) {
							s += "<h2>" + item.msgcontent + "</h2>";
						} else if("image" == item.msgtype) {
							s += "<img width='300px' height='300px' src='${pageContext.request.contextPath}/" + item.msgcontent + "'</>";
						} else if("voice" == item.msgtype) {
							s += "<audio controls src='" + item.msgcontent + "'/>";
						}
						s += "</div>";
					});
					alert(s)
					$("#viewList").html(s);
					$("#replysid").html(sid);
					$("#replysid").attr('value', sid);
				});
			}

			function changeLen() {
				$("#length").html($("#msgcontent").val().length);
			}

			function formajax() {
				var value = $("#replysid").attr('value');
				if(value == "" || value == null) {
					alert("请选择回复的会话及点击[查看会话]");
					return;
				}
				$('#replyform').ajaxSubmit({
					type: 'post',
					success: function(data) {
						sessionviewaction($("#replysid").attr("value"));
					}
				});

				//				var s = $('#replyform').serialize();
				//				alert(s);
				//				$.ajax({
				//					//几个参数需要注意一下
				//					type: "POST", //方法类型
				//					dataType: "json", //预期服务器返回的数据类型
				//					url: "${pageContext.request.contextPath}/custom/reply.action", //url
				//					data: s,
				//					success: function(result) {
				//						sessionviewaction($("#replysid").attr("value")); //更新会话列表
				//					},
				//					error: function() {
				//						alert("回复失败异常！");
				//					}
				//				});
			}
		</script>
	</head>

	<body>
		<div><span>客服编号:${data.custom.customid}</span><span>客服状态:${data.custom.cstate}</span>
		</div>
		<div>
			<table>
				<tr>
					<td>
						<div>
							<div align="center">
								<h2>会话列表</h2>
								<table id='wesessionTab' align="center" style="border: 1px solid #a9c9e2">
									<tr>
										<th>操作</th>
										<th>微信号</th>
										<th>时间</th>
										<th>状态</th>
									</tr>

									<c:forEach items="${data.slist }" var="session">
										<tr>
											<td>
												<input type='button' value='关闭' />
												<input type='button' value='查看详情' onclick='sessionviewaction(${session.id})' />
											</td>
											<td>${session.id} </td>
											<td>${session.openid }</td>
											<td>${session.opentime}</td>
											<td>${session.cstate}</td>
										</tr>
									</c:forEach>
								</table>

							</div>
					</td>
					<td>
						<div>
							<h2>会话详情列表</h2>
							<div id="viewList" align="right" style="border: 1px solid #adcd3c; background: #f2fddb">
								<!--<div align="left" style="border: 1px solid #adcd3c; background: #f2fddb">
									<h2>
									用户openid12346号码 <span>2019/1/14 17:05:30</span>
								</h2>
									<font>发送内容:我发现数据库系统的文件删除不上去我发现数据库系统的文件删除不上去 </br>我发现数据库系统的文件删除不上去我发现数据库系统的文件删除不上去
										</br>我发现数据库系统的文件删除不上去
									</font>
									<div>
										一段语音
										<audio controls src="http://ra01.sycdn.kuwo.cn/resource/n3/32/56/3260586875.mp3"></audio>
									</div>
								</div>
								<div align="right" style="border: 1px solid #adcd3c; background: #e8f5fe">
									<h2>
									客服人员用户名 <span>2019/1/14 17:05:40</span>
								</h2> 解决方案如下图所示:
								</div>
								<div align="right" style="border: 1px solid #adcd3c; background: #e8f5fe">
									<h2>
									客服人员用户名 <span>2019/1/14 17:05:41</span>
								</h2>
									<img src="${pageContext.request.contextPath}/img/aa.png" width="300px" height="300px" />
								</div>-->
							</div>

						</div>

					</td>
				</tr>
			</table>
			<div align="right">
				<form id="replyform" action="${pageContext.request.contextPath}/custom/reply.action" method="post" enctype="multipart/form-data">
					<input type="hidden" name="sid" id="replysid" align="right" />
					<div class="weui-cell">
						<div class="weui-cell__bd">
							<textarea id="msgcontent" class="weui-textarea" placeholder="请输入回复内容" maxlength="200" name="msgcontent" rows="3" onkeyup="changeLen()"></textarea>
							<div class="weui-textarea-counter">
								<span id="length">0</span>/200
							</div>
						</div>
					</div>
					<div align="right">
						<input type="file" name="file" value="发送图片" align="right" />
					</div>
					<div align="right">
						<input type="button" value="回复" align="right" onclick="formajax()" />
					</div>

				</form>

			</div>
			</div>
	</body>

</html>