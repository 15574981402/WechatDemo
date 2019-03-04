package cn.yckj;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64.Encoder;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

import cn.yckj.service.ICustomService;
import cn.yckj.service.impl.CustomServiceImpl;
import cn.yckj.servlet.SpringContextUtil;
import weixin.popular.api.API;
import weixin.popular.api.CustomserviceAPI;
import weixin.popular.api.MenuAPI;
import weixin.popular.api.SnsAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.customservice.KFAccount;
import weixin.popular.bean.customservice.KFAccount.KF_List;
import weixin.popular.bean.menu.Button;
import weixin.popular.bean.menu.Menu;
import weixin.popular.bean.menu.MenuButtons;
import weixin.popular.bean.token.Token;
import weixin.popular.client.LocalHttpClient;

public class MenuTest {
	Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
	private String Menu_httpurl="http://c80cd482.ngrok.io/";
	public static void main(String[] args) throws Exception {
	}
	@Test
	public void testA() throws UnsupportedEncodingException {
		Token t = TokenAPI.token("wx39ac19816b9bb92a", "4307de092110f508ab6026e65c971e25");
		Button button1 = new Button("click", "  服务", "key_1", "", "", "", "");
		ArrayList<Button> arrayList = new ArrayList<Button>();
		arrayList.add(new Button("click", "常见问题自动回复", "key_hf", "", "", "", ""));
		arrayList.add( 
				new Button("view", "问题填报", "key_vt", Menu_httpurl+"/cn.yckj.crm/problem/sb.action", "", "", ""));
		arrayList.add(
				new Button("view", "系统优化申请", "key_hf", Menu_httpurl+"/cn.yckj.crm/problem/yh.action", "", "", ""));
		arrayList.add(new Button("click", "客服咨询", "key_kf", "", "", "", ""));
		button1.setSub_button(arrayList);
		Button[] button = new Button[2];
		button[0] = button1;
		Button button2 = new Button("click", "扩展业务", "key_1", "", "", "", "");
		ArrayList<Button> subButton = new ArrayList<Button>();
		//TODO 还需修改 参考view获得openid问题及网页授权
		subButton.add(new Button("view", "绑定/注册", "", Menu_httpurl+"/cn.yckj.crm/user/binder.action", "", "", ""));
		//
		button2.setSub_button(subButton);
		button[1] = button2;
		MenuButtons menuButtons = new MenuButtons();
		menuButtons.setButton(button);
		BaseResult menuCreate = MenuAPI.menuCreate(t.getAccess_token(), menuButtons);
		System.out.println(menuCreate.getErrcode());
	}

//	@Test
//	public void testCurA() {
//		Token t = TokenAPI.token("wx767a60ba3b057d2a", "904c95cfa5aa4d3e35f52405059d91f8");
//		KFAccount getkflist = CustomserviceAPI.getkflist(t.getAccess_token());
//		for (KF_List iterable_element : getkflist.getKf_list()) {
//			System.out.println(iterable_element.getKf_account());
//		}
//	}
//
//	@Test
//	public void testCur() {
//		Token t = TokenAPI.token("wx767a60ba3b057d2a", "904c95cfa5aa4d3e35f52405059d91f8");
//		String access_token = t.getAccess_token();
//		System.out.println(access_token);
//
//		String s = String.format("{\"kf_account\":\"%1s\",\"nickname\":\"%2s\"}", "test1@gh_abf3f4eb8f13", "张客服");
//		HttpUriRequest a = RequestBuilder.post().setHeader(jsonHeader)
//				.setUri("https://api.weixin.qq.com" + "/customservice/kfaccount/add")
//				.addParameter("access_token", API.accessToken(access_token))
//				.setEntity(new StringEntity(s, Charset.forName("utf-8"))).build();
//		BaseResult executeJsonResult2 = LocalHttpClient.executeJsonResult(a, BaseResult.class);
//		// BaseResult kfaccountAdd = CustomserviceAPI.kfaccountAdd(access_token,
//		// , );
//		// System.out.println(kfaccountAdd.getErrcode());
//
//		String postJsonData = String.format("{\"kf_account\":\"%1s\",\"invite_wx\":\"%2s\"}", "test1@gh_abf3f4eb8f13",
//				"oxTjG0zP77Qui9aMVYbcWSH3imxY");
//		HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(jsonHeader)
//				.setUri("https://api.weixin.qq.com" + "/customservice/kfaccount/inviteworker")
//				.addParameter("access_token", API.accessToken(access_token))
//				.setEntity(new StringEntity(postJsonData, Charset.forName("utf-8"))).build();
//		BaseResult executeJsonResult = LocalHttpClient.executeJsonResult(httpUriRequest, BaseResult.class);
//		System.out.println(executeJsonResult.getErrcode());
//
//	}
}
