package cn.yckj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.yckj.entity.WEUser;
import cn.yckj.service.IUserService;
import cn.yckj.utils.CommonUtil;

@Controller
public class UserAction {
	@Autowired
	private IUserService iuserservie ;
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView login(WEUser u,HttpServletRequest r,HttpServletResponse re) throws Exception {
		//
		u.setUsertime(CommonUtil.getDate());
		Integer insert = iuserservie.insert(u);
		r.getSession().setAttribute("loginin", u);
		return  new ModelAndView("main");
	}
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public ModelAndView login(String openid,HttpServletRequest r,HttpServletResponse re) throws Exception {
		Map<String, String> hashMap = new HashMap<String ,String>();
		hashMap.put("openid",openid);
		hashMap.put("msg", "");
		return  new ModelAndView("user/login","user",hashMap);
	}
	@RequestMapping(value = "/user/binder", method = RequestMethod.GET)
	public ModelAndView binder(HttpServletRequest r,HttpServletResponse re) throws Exception {
		String openid = (String)r.getSession().getAttribute("fromUserName");
		System.out.println("code"+ r.getParameter("code"));
		WEUser u=iuserservie.findbyopenid(openid);
		if(u!=null){
			r.getSession().setAttribute("loginin", u);
			return new ModelAndView("main");
		}
		
		Map<String, String> hashMap = new HashMap<String ,String>();
		hashMap.put("openid",openid);
		hashMap.put("msg", "");
		return  new ModelAndView("user/binder","user",hashMap);
	}
	@RequestMapping(value = "/user/binder", method = RequestMethod.POST)
	public ModelAndView binder(WEUser u,HttpServletRequest r,HttpServletResponse re) throws Exception {
		String username = u.getUsername();
		String password = u.getPassword();
		System.out.println(u.getOpenid());
		WEUser login=iuserservie.login(username,password);
		if(login==null){
			u.setMsg("绑定的用户不存在");
			return new ModelAndView("user/binder","user",u);
		}
		login.setOpenid(u.getOpenid());
		iuserservie.updateOpenid(u.getOpenid(),username,password);
		r.getSession().setAttribute("loginin", login);
		return  new ModelAndView("main");
	}
	
}
