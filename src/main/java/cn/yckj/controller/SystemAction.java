package cn.yckj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.yckj.entity.WESystem;
import cn.yckj.service.IWESystemService;

@Controller
public class SystemAction {
	@Autowired
	private  IWESystemService systemService;
	@ResponseBody
	@RequestMapping(value="/system/list",method=RequestMethod.POST,produces="html/text;charset=utf-8")
	public String list(){
		List<WESystem> list = systemService.list();
		return JSONArray.toJSONString(list);
	}
	
}
