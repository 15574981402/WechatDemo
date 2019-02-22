package com.it.boot.util;

/**
 * 存放微信相关的常量
 * @author admin
 */
public class WeixinConstants {

	//第三方用户唯一凭证  
	public static final String APPID = "wx4e5e168598f72757";
	//第三方用户唯一凭证密钥，即appsecret 
	public static final String APPSECRET = "4970a129df6b83e7e76a0c121db317be";
	//获取AccessToken地址
	public static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//存放AccessToken 2小时自动过期   可以通过任务调度框架来设置该值
	public static String ACCESSTOKEN = "8_oW_Pfaiiu4XJaJrkaVoFZ7icZWCmcXpJMtPoMmO89nqeRdqqsSaoNq2LMlveIGEivU-JQwNmeRWbhtu4TClBrFmW6JtOGhWr3tEJA2rP8wt0eaTPjeB74YFxWCdyJQVbFbsyAUHQzStfdLb0QHSbAFAZQB";
	/**获取微信用户唯一标识openid的请求*/
	public static final String  AUTH_GET_OID="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/**授权请求*/
	public static final String  AUTH_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	public static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/** 只有创建菜单时CREATE_MENU_URL是post请求*/
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	public static final String GET_CURRENT_SELFMENU_INFO = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
	
	
	
}
