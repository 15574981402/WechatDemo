package cn.it.weixin.web.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.it.boot.util.HttpClientUtil;
import com.it.boot.util.WeixinConstants;

public class WeixinAuthFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;   
        //如果session中已经存在微信号了，就不用获取了，否则要获取，获取到以后要存放sesion
        String fromUserName = (String) hRequest.getSession().getAttribute("fromUserName");
        if (fromUserName == null){
        	//只有在微信端才做里面的操作
			String agent = hRequest.getHeader("User-Agent");
            if (agent != null && agent.toLowerCase().indexOf("micromessenger") >= 0){
                String code = request.getParameter("code");
                String state = request.getParameter("state");
                //如果code不为空，scope为base,scope为userInfo代表用户已经同意
                if (code != null && state != null && state.equals("1")){
                    // 通过Code获取openid来进行授权
                    String url =  WeixinConstants.AUTH_GET_OID.replace("APPID", WeixinConstants.APPID)
                    		.replace("SECRET", WeixinConstants.APPSECRET).replace("CODE", code);
                    String json = HttpClientUtil.httpGet(url);
                    System.out.println(json);
                    String openid = (String) JSONObject.parseObject(json, Map.class).get("openid");
                    hRequest.getSession().setAttribute("fromUserName", openid);
                    System.out.println("openid:"+openid);
                }else{
                    String path = hRequest.getRequestURL().toString(); //
                    String query = hRequest.getQueryString();
                    if (query != null){
                        path = path + "?" + query;
                    }
                    System.out.println("path:"+path);
                    String uri = WeixinConstants.AUTH_URL.replace("APPID", WeixinConstants.APPID)
                    		.replace("REDIRECT_URI", URLEncoder.encode(path, "UTF-8"))
                            .replace("SCOPE", "snsapi_userinfo").replace("STATE", "1");
                    hResponse.sendRedirect(uri);
                    return;
                }
            }
        }
        chain.doFilter(hRequest, hResponse);
    }

	@Override
	public void destroy() {}

}
