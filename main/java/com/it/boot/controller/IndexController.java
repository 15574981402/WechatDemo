package com.it.boot.controller;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.it.boot.util.HttpClientUtil;
import com.it.boot.util.WeiXinUtil;
import com.it.boot.util.WeixinConstants;
import com.it.boot.util.XmlpullParserUtil;

@Controller
public class IndexController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void signature(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		System.out.println("signature:" + signature);
		System.out.println("timestamp:" + timestamp);
		System.out.println("nonce:" + nonce);
		System.out.println("echostr:" + echostr);
		boolean checkSignature = WeiXinUtil.checkSignature("weixin", signature, timestamp, nonce);
		if (checkSignature) {
			System.out.println("微信接入成功");
			response.getWriter().println(echostr);
		} else {
			throw new RuntimeException("接入失败");
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		// 获取请求的内容 从请求的输入流中获取并打印
		ServletInputStream inputStream = request.getInputStream();
		// 字节流转换为字符流
		InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
		/* BufferedReader br = new BufferedReader(reader); //把字符流包装一下 String
		 * content = null;//一直读取直到读完 while((content = br.readLine())!=null){
		 * System.out.println(content); }*/
		/* 2. 从请求的流里面解析XML,拼接要返回的字符串格式
		 * Map<String, String> map = XmlpullParserUtil.parse(reader);
		 * PrintWriter writer = response.getWriter();
		 * writer.write(buildeXmlContent(map));
		 */
		// 3.通过jsp模板完成原理通过PrintWriter写文本的方式,jsp会把里面的内容使用PrintWriter一个一个的写出去
		Map<String, String> map = XmlpullParserUtil.parse(reader);
		print(map);
		/*// 修改返回的时间戳
		map.put("CreateTime", new Date().getTime() + "");*/
		// 1.如果用户关注了公众号
//		if ("event".equals(map.get("MsgType"))&& "subscribe".equals(map.get("Event"))){
//			map.put("Content", "yckj欢迎你...");
//			map.put("MsgType", "text");
//		}
		
		if ("event".equals(map.get("MsgType"))&& "TEMPLATESENDJOBFINISH".equals(map.get("Event")) && "success".equals(map.get("Status"))) {
			return new ModelAndView("weixin");
		}
		/*
		 * Ticket---->gQHT8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLVp4NGttY09maGsxQW1qWTFxMW0AAgSWGLNaAwSAOgkA
			CreateTime---->1521687535
			EventKey---->123546
			Event---->SCAN
			ToUserName---->gh_ea36498508fa
			FromUserName---->oTHLR1OWem94aO2k2AAP9R1vcv-c
			MsgType---->event
			扫描二维码事件
		 * */
		System.out.println("--------------");
		if ("event".equals(map.get("MsgType")) && map.get("Ticket")!= null){
			String key = map.get("EventKey");
			System.out.println("EventKey:"+key);
			int index = key.lastIndexOf("-");
			key.substring(index+1, key.length()-1);
			//根据 二维码事件key 查询订单数据 发送给客户
			
			sendCustom();
		}
		
		/*new ModelAndView(String viewName,String modelName,Object modelObject);
		 String viewName视图名称,加上前缀和后缀形成完整的地址
		 modelName 模型名称 modelObject 数据模型
		 由于配置mvc视图解析器InternalResourceViewResolver /WEB-INF/jsp/weixin.jsp*/
		return new ModelAndView("weixin", "map", map);
	}
	
	public void sendCustom() {
		String urlSend ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		String url = urlSend
				.replace("ACCESS_TOKEN", WeixinConstants.ACCESSTOKEN);
		
		ArrayList<Map<String,Object>> articles = new ArrayList<>() ;
		Map<String,Object> article = new HashMap<>();
		article.put("title","客户消息");
		article.put("description","描述信息");
		article.put("url"," http://weixinyckj.tunnel.qydev.com/customer/list.action");
		//article.put("picurl","客户消息");
		articles.add(article);
		
		Map<String,Object> news = new HashMap<>();
		news.put("articles", articles);
		
		Map<String,Object> root = new HashMap<>();
		root.put("touser", "oTHLR1OWem94aO2k2AAP9R1vcv-c");
		root.put("msgtype", "news");
		root.put("news", news);
		String jsonParam = JSONObject.toJSONString(root);
		String jsonStr = HttpClientUtil.httpPost(url,jsonParam);
		System.out.println(jsonStr);
	}
	private String buildeXmlContent(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName>" + map.get("FromUserName") + "</ToUserName>");//
		sb.append("<FromUserName>" + map.get("ToUserName") + "</FromUserName>");
		sb.append("<CreateTime>" + new Date().getTime() + "</CreateTime>");
		String msgType = map.get("MsgType");
		if (msgType.equals("image")) {
			sb.append("<MsgType><![CDATA[image]]></MsgType>");
			sb.append("<Image>");
			sb.append("<MediaId>" + map.get("MediaId") + "</MediaId>");
			sb.append("</Image>");
		} else if (msgType.equals("voice")) {
			sb.append("<MsgType><![CDATA[voice]]></MsgType>");
			sb.append("<Voice>");
			sb.append("<MediaId>" + map.get("MediaId") + "</MediaId>");
			sb.append("</Voice>");

		} else {
			// 把要处理的都给处理了,剩余的就是文本
			sb.append("<MsgType><![CDATA[text]]></MsgType>");
			sb.append("<Content>" + map.get("Content") + "</Content>");
		}

		sb.append("</xml>");
		return sb.toString();
	}

	private void print(Map<String, String> map) {
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "---->" + value);
		}
	}
}
