package cn.yckj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.yckj.entity.WESession;
import cn.yckj.service.ISessionService;

@Controller
public class SessionAction {
	@Autowired
	private ISessionService isessionservice;
	@RequestMapping(value="/session/list",method=RequestMethod.POST,produces="html/text;charset=utf-8")
	@ResponseBody
	public String  listBySid(String cid,Model m) throws Exception {
		List<WESession>  slist=isessionservice.listByCid(cid);
		return JSONObject.toJSONString(slist);
	}
}
