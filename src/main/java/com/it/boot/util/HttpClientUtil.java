package com.it.boot.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 */
public class HttpClientUtil {

	/** 发送httpGet请求到url所对应的地址,并且获取Json类型的数据*/
	public static String httpGet(String url) {
		try {
			HttpClient execution =  HttpClients.createDefault(); // http请求执行对象
			HttpGet httpGet = new HttpGet(url);  // 执行请求对象
			HttpResponse response = execution.execute(httpGet); 
			//状态码 200 404 500 302 400
			// int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();//响应额内容是放到HttpEntity里面的
			return EntityUtils.toString(entity); // java对象转成json串
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	/*** 发送Post请求到url并携带参数jsonParam,返回值为JSON字符串*/
	public static String httpPost(String url,String jsonParam) {
		try {
			HttpClient execution =  HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(jsonParam, "utf-8"));//设置参数也是通过一个Entity
			HttpResponse response = execution.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
