<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统优化界面</title>
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
				yhsystemList();
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

			function changeLenyh() {
				$("#yhlength").html($("#yhpdesc").val().length);
			}

			function yhsystemList() {
				$.post("${pageContext.request.contextPath}/system/list.action", {}, function(data) {
					var vlist = JSON.parse(data);
					$("#yhsystemtype").html(""); //清空 
					var optionList = "<option >请选择优化系统</option>";
					$.each(vlist, function(i, system) {
						optionList += "<option value='" + system.systemtype + "'>" + system.systemname + "</option>"
					});
					$("#yhsystemtype").html(optionList);
				});
			}


			function yhmodeList() {

				$.post("${pageContext.request.contextPath}/mode/received.action", {
					"systemtype": $("#yhsystemtype").val()
				}, function(data) {
					var vlist = JSON.parse(data);
					var optionList = "<option >请选择优化模块</option>";
					$("#yhpmodule").html("");
					$.each(vlist, function(i, mode) {
						optionList += "<option value='" +mode.moduleid + "'>" + mode.modulename + "</option>"
					});
					$("#yhpmodule").html(optionList);
				});
			}

			//					});
		</script>
	</head>

	<body>
		<!-- 容器 -->
		<div class="weui-tab">
			<div class="weui-navbar">
				<a class="weui-navbar__item weui-bar__item--on weui-tab__bd-item--active" href="#tab1">
					系统优化
				</a>
			</div>
			<div class="weui-tab__bd">

				<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
					<form action="${pageContext.request.contextPath}/problem/yhreceived.action" method="post" enctype="multipart/form-data">
						<input type="hidden" name="puser" value="${openid}" />
						<div class="weui-cells weui-cells_form">
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label"><h2>系统</h2></label>
								</div>
								<div class="weui-cell__bd">
									<h2>
									<select name="systemtype" id="yhsystemtype" class="weui-input" onchange="yhmodeList()">
										<option >请选择优化系统</option>
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
									<select name="pmodule" id="yhpmodule" class="weui-input"  >
										<option >请选择优化模块</option>
									</select>
									</h2>
								</div>
							</div>

							<div class="weui-cells__title">
								<h2>优化描述</h2></div>
							<div class="weui-cell">
								<div class="weui-cell__bd">
									<textarea id="yhpdesc" name="pdesc" class="weui-textarea" placeholder="请输入文本" rows="3" maxlength="200" onkeyup="changeLenyh()"></textarea>
									<div class="weui-textarea-counter"><span id="yhlength">0</span>/200</div>
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

													<div class="weui-uploader__input-box">
														<div><input type="file" name="upfiles">可选附件1</input>
														</div>
														<div><input type="file" name="upfiles">可选附件2</input>
														</div>
														<div><input type="file" name="upfiles">可选附件3</input>
														</div>
													</div>

												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div>
								<input type="submit" value="优化提交" />
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</body>

</html>