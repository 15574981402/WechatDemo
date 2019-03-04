package cn.yckj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

import cn.yckj.entity.WEAutoreply;
import cn.yckj.service.IAutoreplyService;
import cn.yckj.utils.CommonUtil;

@Controller
public class AutoreplyAction {
	@Autowired
	@Qualifier("autoreplyservice")
	private IAutoreplyService iautoreplyservice;
	@RequestMapping(value="/autoreply/insert",method=RequestMethod.POST,produces="html/text;charset=utf-8")
	@ResponseBody
	public String insert(WEAutoreply w){
		String systemtype = w.getSystemtype();
		w.setSystemid(CommonUtil.nextIntValue(iautoreplyservice.maxSystemid(systemtype)));
		iautoreplyservice.insert(w);
		return list(systemtype);
	}
	@RequestMapping(value="/autoreply/update",method=RequestMethod.POST,produces="html/text;charset=utf-8")
	@ResponseBody
	public String	update(WEAutoreply w){
		iautoreplyservice.update(w);
		return list(w.getSystemtype());
	}
	@RequestMapping(value="/autoreply/list",method=RequestMethod.POST,produces="html/text;charset=utf-8")
	@ResponseBody
	public String list(String systemType){
		List<WEAutoreply> autolist = iautoreplyservice.list(systemType);
		return JSONArray.toJSONString(autolist);
	}
}
