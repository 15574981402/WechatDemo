package com.it.boot.util;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * 
 * XmlpullParser相关的帮助类
 *    1 从请求中解析请求内容
 * @author admin
 *
 */
public class XmlpullParserUtil {

	/**
	 * 传入什么参数? Reader?
	 * 	因为我待会儿写出来以后要测试,需要通过一个字符串格式XML来测试,到时可以把字符串转换为StringReader来使用
	 * 返回什么?
	 *    两种方案:
	 *     1 对象方案:抽象BaseMsg,在这里面放都有属性,TextMsg,ImgMsg等继承BaseMsg拥有公共属性,然后可以扩展自己独有的属性.----要写很多类
	 *     2 Map:以标签名为Map的Key,标签的内容为Map的Value,无论是什么消息都可以使用Map来描述(采纳)
	 * @return
	 */
	public static Map<String, String> parse(Reader reader) {
		Map<String, String>  result = new HashMap<>();
		try {
			//获取pull解析器
			XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
			//设置解析的内容
			pullParser.setInput(reader);
			//开始解析
			// XmlPullParser.START_DOCUMENT 文档开始 扫描到<xml>
			// XmlPullParser.END_DOCUMENT 文档结束 </xml>
			// XmlPullParser.START_TAG 标签开始
			// XmlPullParser.END_TAG 标签结束
			
			//获取当前事件类型
			int eventType = pullParser.getEventType();
			//文档没有结束都要一直解析
			while (eventType != XmlPullParser.END_DOCUMENT) {
				//解析的实际是标签开始,但是标签不包含xml
				String tagName = pullParser.getName();
				if (eventType==XmlPullParser.START_TAG && !tagName.equals("xml")) {
					String tagContext = pullParser.nextText();//以标签的下一个文本的方式获取标签的内容
					result.put(tagName, tagContext);
				}
				//当前事件处理完毕后,要推动到下一个事件
				eventType = pullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		// 微信文本消息接收格式
		String xmlContent =  "<xml><ToUserName><![CDATA[gh_ae9b8f50b1b3]]></ToUserName>"
				+ "<FromUserName><![CDATA[oxLXms6cL0ECuVST7vQiDZdg4RbU]]></FromUserName>"
				+ "<CreateTime>1484979981</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[test]]></Content>"
				+ "<MsgId>6377940454165071174</MsgId></xml>";
		
		Map<String, String> map = parse(new StringReader(xmlContent));
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key+"---->"+value);
		}
	}
}
