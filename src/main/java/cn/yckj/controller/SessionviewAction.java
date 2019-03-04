package cn.yckj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.yckj.entity.WESessionview;
import cn.yckj.service.ISessionviewService;

@Controller
public class SessionviewAction {
	@Autowired
	private ISessionviewService isessionviewservice;
	@RequestMapping(value="/sessionview/list",method=RequestMethod.POST,produces="html/text;charset=utf-8")
	@ResponseBody
	public String listBySid(Model m,String sid) throws Exception {
		List<WESessionview>  viewlist=isessionviewservice.list(sid);
		return JSONObject.toJSONString(viewlist);
	}
}
