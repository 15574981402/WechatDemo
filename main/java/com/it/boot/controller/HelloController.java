package com.it.boot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.it.boot.pojo.QueryVo;
import com.it.boot.util.HttpClientUtil;
import com.it.boot.util.WeixinConstants;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String showCustomerList(QueryVo queryVo,Model model) throws Exception {
		
		return "hello";
	}
	@RequestMapping("/binder")
	public String binder(QueryVo queryVo,Model model) throws Exception {
		return "hello";
	}
	
	private String getticket() {
		String ticketUrl ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		String url =ticketUrl
				.replace("TOKEN", WeixinConstants.ACCESSTOKEN);
		// {"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
		Map<String,Object> scene_map = new HashMap<>();
		scene_map.put("scene_id", 123546);
		
		Map<String,Object> action_info_map = new HashMap<>();
		action_info_map.put("scene", scene_map);
		
		Map<String,Object> root = new HashMap<>();
		root.put("expire_seconds", 604800);
		root.put("action_name", "QR_SCENE");
		root.put("action_info", action_info_map);
		
		String jsonParam = JSONObject.toJSONString(root);
		System.out.println(jsonParam);
	    String jsonStr = HttpClientUtil.httpPost(url, jsonParam);
		System.out.println(jsonStr);
		Map<String,String> responseMap = JSONObject.parseObject(jsonStr, Map.class);
		return responseMap.get("ticket");
	}
	@RequestMapping("/ticket")
	public void showqrcode(HttpServletResponse hResponse) throws IOException {
		System.out.println(222);
		String showqrcode ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		String url = showqrcode
				//.replace("TICKET", getticket());
				.replace("TICKET",getticket());
		String jsonStr = HttpClientUtil.httpGet(url);
		//System.err.println(jsonStr);
		hResponse.addHeader("Content-Type", "image/jpg");
		hResponse.getWriter().println(jsonStr);
		
		//GET https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHT8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLVp4NGttY09maGsxQW1qWTFxMW0AAgSWGLNaAwSAOgkA HTTP/1.1
		
	}
}
