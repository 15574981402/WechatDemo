//			var siteUrl = window.location.href;
//			console.log(siteUrl);
$(document).ready(function() {
	$.ajax({
		async: "false",
		type: "GET",
		url: "http://127.0.0.1:8080/cn.yckj.crm/getTicket.action",
		dataType: "json",
		success: function(res) {
			console.log(res);
			wx.config({
				debug: false,
				appId: res.appId,
				timestamp: res.timestamp,
				nonceStr: res.noncestr,
				signature: res.signature,
				jsApiList: res.jsApiList
			}); //end_config
		},
		error: function(res) {
			wx.error(function(res) {
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
				console.debug(res);
			});
		}

	})
})
$(function() {
			$('.uploaderInput').on('click',
				function(event) {
					wx.chooseImage({
						count: 1, // 默认9
						sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
						sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
						success: function(res) {
							var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
							console.debug(localIds);
						}
					});
				});

			});