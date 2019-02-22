package cn.yckj.crm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.it.boot.util.HttpClientUtil;
import com.it.boot.util.WeixinConstants;

public class MenuTest {

	@Test
	public void testAccessToken() {
		try {
			//1 创建一个Http请求的执行对象
			HttpClient execution = HttpClients.createDefault(); ;
			
			//2 要创建执行的请求
			String url = WeixinConstants.GET_ACCESSTOKEN_URL
					.replace("APPID", WeixinConstants.APPID)
					.replace("APPSECRET", WeixinConstants.APPSECRET);
			HttpGet httpGet = new HttpGet(url);
			//3 使用执行对象执行对应的请求,获取响应对象
			HttpResponse response = execution.execute(httpGet);
			//4 从响应对象里面获取想要的内容
			//状态码 200 404 500 302 400
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("statusCode:"+statusCode);
			org.apache.http.HttpEntity entity = response.getEntity();//响应额内容是放到HttpEntity里面的
			String jsonStr = EntityUtils.toString(entity);
			System.out.println(jsonStr);
			Map<String,String> responseMap = JSONObject.parseObject(jsonStr, Map.class);
			Object access_token = responseMap.get("access_token");
			if(access_token != null){
				WeixinConstants.ACCESSTOKEN =(String) access_token; // 不能将值赋值到上面??
				System.out.println(WeixinConstants.ACCESSTOKEN);
			}else{
				throw new RuntimeException("获取access_token失败"+responseMap.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	@Test  //测试临时二维码
	public void ticket() {
		getticket();
/*		{"ticket":"gQHT8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLVp4NGttY09maGsxQW1qWTFxMW0AAgSWGLNaAwSAOgkA",
			"expire_seconds":604800,
			"url":"http:\/\/weixin.qq.com\/q\/02-Zx4kmcOfhk1AmjY1q1m"}*/
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
	@Test
	public void showqrcode() {
		String showqrcode ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		String url = showqrcode
				//.replace("TICKET", getticket());
				.replace("TICKET","gQHT8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLVp4NGttY09maGsxQW1qWTFxMW0AAgSWGLNaAwSAOgkA");
		String jsonStr = HttpClientUtil.httpGet(url);
		System.out.println(jsonStr);
		//GET https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHT8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLVp4NGttY09maGsxQW1qWTFxMW0AAgSWGLNaAwSAOgkA HTTP/1.1
	}
	
	
	
	/**
	 * http请求方式：GET
	https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN
	  参数:access_token 前面已经获取到了
	 */
	@Test
	public void testGetMenu() {
		// 7_yajSw5etoaTLQzJTcTWIyogvv7eS-pfK60tgf5WvkPtRZBCsxrSHLIqgf1W8ZBtn-ByJEY1QhlaK3Z27dgAn9uVLbAqidftDkZq0moAO-t2FVP8XFvT2FiX9zBfJf_3zWSJaut8GFsHmefG3DBQiAJATRG
		String url = WeixinConstants.GET_MENU_URL
				.replace("ACCESS_TOKEN", WeixinConstants.ACCESSTOKEN);
		System.out.println(WeixinConstants.ACCESSTOKEN);
	    String jsonStr = HttpClientUtil.httpGet(url);
		System.out.println(jsonStr);
	}
	@Test
	/**发送的模板消息*/
	public void send() {
		String urlSend ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		String url = urlSend
				.replace("ACCESS_TOKEN", WeixinConstants.ACCESSTOKEN);
		
		Map<String,String> param = new HashMap<>();
		param.put("value", "测试模板数据");
		param.put("color", "#173198");
		param.put("url", "http://weixin.qq.com/download"); //url 跳转不了
		
//		"url":"http://weixin.qq.com/download",
//	    "topcolor":"#FF0000",
	    param.put("url", "http://weixin.qq.com/download");
		//param.put("topcolor", "#FF0000");
		Map<String,Object> dataparam = new HashMap<>();
		dataparam.put("keyword", param);	
		Map<String,Object> data = new HashMap<>();
		data.put("data", dataparam);
		data.put("touser", "oTHLR1OWem94aO2k2AAP9R1vcv-c");
		//data.put("touser", "oTHLR1GWt68skK7w9Ri3lGSBT-6o");
		data.put("template_id","WLZcIiZIkpxMEMO6OwkUTPCziHOJuXmEp0X8vaIMyV8");	
		String jsonParam = JSONObject.toJSONString(data);
	    String jsonStr = HttpClientUtil.httpPost(url,jsonParam);
		System.out.println(jsonStr);
	}
		
		@Test
		/**发送的消息*/
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
	
	@Test
	public void setTemplet() {
		String urlSend ="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
		String url = urlSend
				.replace("ACCESS_TOKEN", WeixinConstants.ACCESSTOKEN);
		Map<String,Object> root = new HashMap<>();
		root.put("WLZcIiZIkpxMEMO6OwkUTPCziHOJuXmEp0X8vaIMyV8", "2");
		
		String jsonParam = JSONObject.toJSONString(root);
		System.out.println(jsonParam);
	    String jsonStr = HttpClientUtil.httpPost(url,jsonParam);
		System.out.println(jsonStr);
	}
	
		@Test
		public void getTemplet() {
			String urlSend ="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
			String url = urlSend
					.replace("ACCESS_TOKEN", WeixinConstants.ACCESSTOKEN);
			
		    String jsonStr = HttpClientUtil.httpGet(url);
			System.out.println(jsonStr);
		}
	@Test
	public void testCreateMenu() {
		String url = WeixinConstants.CREATE_MENU_URL.replace("ACCESS_TOKEN", WeixinConstants.ACCESSTOKEN);
		
//		Map<String,Object> root = new HashMap<>();
//		ArrayList<Object> oneList = new ArrayList<>() ;
//		Map<String,Object> twoMap = new HashMap<>();
//		
//		ArrayList<Object> twoList = new ArrayList<>() ;
//		Map<String,Object> threeMap01 = new HashMap<>();
//		threeMap01.put("type", "view");
//		threeMap01.put("name", "yckj公司首页");
//		threeMap01.put("url", "http://weixinyckj.tunnel.qydev.com/hello.action");
//		Map<String,Object> threeMap02 = new HashMap<>();
//		threeMap02.put("type", "view");
//		threeMap02.put("name", "百度首页");
//		threeMap02.put("url", "http://www.baidu.com");
//		twoList.add(threeMap01);
//		twoList.add(threeMap02);
//		
//		twoMap.put("sub_button", twoList);
//		twoMap.put("name", "扫码");
//		oneList.add(twoMap);
//		root.put("button", oneList);
//		String jsonParam = JSONObject.toJSONString(root);
		
		String jsonParam = getJsonParam();
		System.out.println(jsonParam);
		String httpPost = HttpClientUtil.httpPost(url, jsonParam );
		System.out.println(httpPost);
	}
	public String getJsonParam() {
		Map<String,String> sub_button1 = new HashMap<>();
		sub_button1.put("type", "view");
		sub_button1.put("name", "百度");
		sub_button1.put("url", "https://www.baidu.com");
		Map<String,String> sub_button2 = new HashMap<>();
		sub_button2.put("type", "view");
		sub_button2.put("name", "yckj");
		sub_button2.put("url", "http://weixinyckj.tunnel.qydev.com/hello.action");
		Map<String,String> sub_button3 = new HashMap<>();
		sub_button3.put("type", "view");
		sub_button3.put("name", "yckj银电");
		sub_button3.put("url", "http://www.hnycyd.com/");
		ArrayList<Map<String,String>> sub_button_List = new ArrayList<>() ;
		sub_button_List.add(sub_button1);
		sub_button_List.add(sub_button2);
		sub_button_List.add(sub_button3);
		
		Map<String,String> sub_button4 = new HashMap<>();
		sub_button4.put("type", "view");
		sub_button4.put("name", "客户管理");
		sub_button4.put("url", "http://weixinyckj.tunnel.qydev.com/customer/list.action");
		Map<String,String> sub_button5 = new HashMap<>();
		sub_button5.put("type", "view");
		sub_button5.put("name", "绑定微信用户");
		sub_button5.put("url", "http://weixinyckj.tunnel.qydev.com/customer/binder.action");
		ArrayList<Map<String,String>> sub_button_List2 = new ArrayList<>() ;
		sub_button_List2.add(sub_button4);
		sub_button_List2.add(sub_button5);
		
		Map<String,Object> button1 = new HashMap<>();
		button1.put("sub_button", sub_button_List);
		button1.put("name", "菜单");	
		
		Map<String,Object> button2 = new HashMap<>();
//		button2.put("type", "click");
//		button2.put("name", "今日歌曲");
//		button2.put("key", "V1001_TODAY_MUSIC");
		button2.put("sub_button", sub_button_List2);	
		button2.put("name", "操作");	
		ArrayList<Map<String,Object>> button_List = new ArrayList<>();
		button_List.add(button1);
		button_List.add(button2);
		
		Map<String,List> root = new HashMap<>();
		root.put("button", button_List);
		String jsonParam = JSONObject.toJSONString(root);
		return jsonParam;
	}
}
