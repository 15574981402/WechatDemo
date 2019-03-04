package cn.yckj.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.yckj.entity.WEMode;
import cn.yckj.service.IModeService;
import weixin.popular.api.MenuAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.menu.Button;
import weixin.popular.bean.menu.MenuButtons;
import weixin.popular.bean.token.Token;
import weixin.popular.support.TokenManager;
@Controller
public class ModeAction {
	@Value("${Menu.httpurl}")
	private String MENU_HTTPURL;
	@Autowired
	private IModeService imodeservice;
	@RequestMapping(value="/mode/received",method=RequestMethod.POST,produces="html/text;charset=utf-8")
	@ResponseBody
	public String received(String systemtype ){
		System.out.println("systemtype:"+systemtype);
		List<WEMode> list = imodeservice.list(systemtype);
		System.out.println("mode:"+list.size());
		return JSONArray.toJSONString(list);
	}
	
	@RequestMapping(value="/mode/received",method=RequestMethod.GET)
	public void menu(  ){
		Button button1 = new Button("click", "  服务", "key_1", "", "", "", "");
		ArrayList<Button> arrayList = new ArrayList<Button>();
		arrayList.add(new Button("click", "常见问题自动回复", "key_hf", "", "", "", ""));
		arrayList.add( 
				new Button("view", "问题填报", "key_vt", MENU_HTTPURL+"/cn.yckj.crm/problem/sb.action", "", "", ""));
		arrayList.add(
				new Button("view", "系统优化申请", "key_hf", MENU_HTTPURL+"/cn.yckj.crm/problem/yh.action", "", "", ""));
		arrayList.add(new Button("click", "客服咨询", "key_kf", "", "", "", ""));
		button1.setSub_button(arrayList);
		Button[] button = new Button[2];
		button[0] = button1;
		Button button2 = new Button("click", "扩展业务", "key_1", "", "", "", "");
		ArrayList<Button> subButton = new ArrayList<Button>();
		subButton.add(new Button("view", "绑定/注册", "", MENU_HTTPURL+"/cn.yckj.crm/user/binder.action", "", "", ""));
		//
		button2.setSub_button(subButton);
		button[1] = button2;
		MenuButtons menuButtons = new MenuButtons();
		menuButtons.setButton(button);
		BaseResult menuCreate = MenuAPI.menuCreate(TokenManager.getDefaultToken(), menuButtons);
		System.out.println(menuCreate.getErrcode());
	
	}
}
