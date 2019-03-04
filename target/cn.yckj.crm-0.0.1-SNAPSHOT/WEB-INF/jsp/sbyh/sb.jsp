<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
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
		<!-- 如果使用了某些拓展插件还需要额外的JS -->
		<script src="${pageContext.request.contextPath}/js/we/swiper.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/we/city-picker.min.js"></script>
		<!-- JSSDK -->
		<script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				systemList();
			});
			//					$(document).ready(function() {

			//				console.debug('调用ajax加载config配置');
			//				$.ajax({
			//					async: 'false',
			//					type: 'GET',
			//					url: 'http://127.0.0.1:8080/cn.yckj.crm/getTicket.action',
			//					dataType: 'json',
			//					success: function(res) {
			//						console.debug(res);
			//						wx.config({
			//							debug: false,
			//							appId: res.appId,
			//							timestamp: res.timestamp,
			//							nonceStr: res.noncestr,
			//							signature: res.signature,
			//							jsApiList: res.jsApiList
			//						}); //end_config
			//					},
			//					error: function(res) {
			//						console.debug(res);
			//					}
			function systemList() {
				$.post("${pageContext.request.contextPath}/system/list.action", {}, function(data) {
					var vlist = JSON.parse(data);
					$("#systemtype").html(""); //清空 
					var optionList = "<option >请选择申报系统</option>";
					$.each(vlist, function(i, system) {
						optionList += "<option  value='" + system.systemtype + "'>" + system.systemname + "</option>"
					});
					$("#systemtype").html(optionList);
				});
			}


			function modeList() {
				$.post("${pageContext.request.contextPath}/mode/received.action", {
					"systemtype": $("#systemtype").val()
				}, function(data) {
					var vlist = JSON.parse(data);
					var optionList = "<option >请选择问题模块</option>";
					$("#pmodule").html("");
					$.each(vlist, function(i, mode) {
						optionList += "<option value='" +mode.moduleid + "'>" + mode.modulename + "</option>"
					});
					$("#pmodule").html(optionList);
				});
			}

			function changeLen() {
				$("#length").html($("#pdesc").val().length);
			}

			//					});
		</script>
	</head>

	<body>
		<!-- 容器 -->
		<div class="weui-tab">
			<div class="weui-navbar">
				<a class="weui-navbar__item weui-bar__item--on" href="#tab1">
					问题填报
				</a>
			</div>
			<div class="weui-tab__bd">
				<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">

					<form action="${pageContext.request.contextPath}/problem/sbreceived.action" method="post" enctype="multipart/form-data">
						<input type="hidden" name="puser" value="${openid}" />
						<div class="weui-cells weui-cells_form">
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label"><h2>系统</h2></label>
								</div>
								<div class="weui-cell__bd">
									<h2>
									<select name="systemtype" id="systemtype" class="weui-input" onchange="modeList()" >
										<option >请选择申报系统</option>
									</select>
									</h2>
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label"><h2>模块</h2></label>
								</div>
								<div class="weui-cell__bd">
									<h2>
									<select name="pmodule" id="pmodule" class="weui-input" >
										<option >请选择问题模块</option>
									</select>
									</h2>
								</div>
							</div>

							<div class="weui-cells__title">
								<h2>问题描述</h2></div>
							<div class="weui-cell">
								<div class="weui-cell__bd">
									<textarea id="pdesc" name="pdesc" class="weui-textarea" placeholder="请输入文本" rows="3" maxlength="200" onkeyup="changeLen()"></textarea>
									<div class="weui-textarea-counter"><span id="length">0</span>/200</div>
								</div>
							</div>

							<div class="weui-cells weui-cells_form">
								<div class="weui-cell">
									<div class="weui-cell__bd">
										<div class="weui-uploader">
											<div class="weui-uploader__hd">
												<p class="weui-uploader__title">附件上传</p>
												<div class="weui-uploader__info">
												</div>
												<div class="weui-uploader__bd"> 
													<ul class="weui-uploader__files" id="uploadImage"></ul>

													<div class="">
														<div class="weui-uploader__bd"><input type="file" name="upfiles"/>
														</div>
														<div class="weui-uploader__bd"><input type="file" name="upfiles"/>
														<div class="weui-uploader__bd"><input type="file" name="upfiles"/>
														</div>
													</div>

												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div>
								<input type="submit" value="问题提交" />
							</div>
					</form>
					</div>

				</div>
			</div>
		</div>

	</body>

</html>