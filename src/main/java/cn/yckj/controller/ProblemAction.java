package cn.yckj.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.yckj.entity.WEFile;
import cn.yckj.entity.WEProblem;
import cn.yckj.service.IFileService;
import cn.yckj.service.IProblemService;
import cn.yckj.service.IUserService;
import cn.yckj.utils.CommonUtil;
import cn.yckj.utils.DateUtils;

@Controller
public class ProblemAction {
	private static final String String = null;
	@Value("${ProblemAction.SB_PATH}")
	private String SB_PATH;
	@Value("${ProblemAction.YH_PATH}")
	private String YH_PATH;
	@Autowired
	private IProblemService iproblemservice;
	@Autowired
	private IFileService ifileservice;
	@Autowired
	private IUserService iuserservie ;
	
	@RequestMapping("/problem/sb")
	public ModelAndView sb(HttpServletRequest r) throws IOException{
		String openid =(String) r.getSession().getAttribute("fromUserName");
		if(iuserservie.exist(openid)){
			return new ModelAndView("sbyh/sb","openid",openid);
		}else{
			Map<String, String> hashMap = new HashMap<String ,String>();
			hashMap.put("openid",openid);
			hashMap.put("msg", "请先进行用户的登录/或注册");
			return new ModelAndView("/user/binder","user",hashMap);
		}
	}
	@RequestMapping("/problem/yh")
	public ModelAndView yh(HttpServletRequest r) throws IOException{
		String openid =(String) r.getSession().getAttribute("fromUserName");
		if(iuserservie.exist(openid)){
			return new ModelAndView("sbyh/yh","openid",openid);
		}else{
			Map<String, String> hashMap = new HashMap<String ,String>();
			hashMap.put("openid",openid);
			hashMap.put("msg", "请先进行用户的登录/或注册");
			return new ModelAndView("/user/binder","user",hashMap);
		}
	}
	
	@RequestMapping(value="/problem/sbreceived",method=RequestMethod.POST)
	public ModelAndView received(@RequestParam("upfiles") MultipartFile[] files,WEProblem w,HttpServletRequest r) throws IOException{
		String filetime = CommonUtil.getDate();
		w.setPusertime(filetime);
		String puser = w.getPuser();
		if(StringUtils.isBlank(puser)){
			puser=(String) r.getSession().getAttribute("fromUserName");
		}
		w.setPurl("");
		//上传文件
		for (MultipartFile multipartFile : files) {
			InputStream inputStream = multipartFile.getInputStream();
			String name = multipartFile.getOriginalFilename();
			if(StringUtils.isBlank(name)){
				continue;
			}
			System.out.println("name:"+name);
			String name2 = multipartFile.getName();
			System.out.println("name2:"+name2);
			String substring = name.substring(name.lastIndexOf("."));
			String path = SB_PATH+CommonUtil.getYear()+"/"+puser;
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			String date2Str = DateUtils.date2Str(new Date(), "yyyyMMddhhmmssSSS");
			String filepath=path+"/"+date2Str+substring;
			File destination=new File(filepath);
			FileUtils.copyInputStreamToFile(inputStream, destination);
			
			WEFile weFile = new WEFile(name, filepath, puser, filetime);
			String maxFileid = ifileservice.maxFileid();
			String nextIntValue = CommonUtil.nextIntValue(maxFileid);
			weFile.setFileid(nextIntValue);
			ifileservice.insert(weFile);
			w.addpurl(nextIntValue);
		}
		w.setPtype(WEProblem.PTYPE_SB);
		w.setPstate(WEProblem.PSTATE_CJ);
		String maxPid=iproblemservice.maxPid();
		w.setPid(CommonUtil.nextIntValue(maxPid));
		System.out.println(w);
		iproblemservice.insert(w);
		return list(puser,WEProblem.PTYPE_SB);
	}
	@RequestMapping("/problem/list")
	public ModelAndView list(String puser,String ptype) throws IOException{
		List<WEProblem> pList=iproblemservice.list(puser,ptype);
		if(WEProblem.PTYPE_SB.equals(ptype)){
			return new ModelAndView("sbyh/sblist","pList",pList);
		}else{//  if(WEProblem.PTYPE_YH.equals(ptype))
			return new ModelAndView("sbyh/yhlist","pList",pList);
		}
	}
	@RequestMapping(value="/problem/yhreceived",method=RequestMethod.POST)
	public ModelAndView yhreceived(@RequestParam("upfiles") MultipartFile[] files,WEProblem w) throws IOException{
		String filetime = CommonUtil.getDate();
		w.setPusertime(filetime);
		String puser = w.getPuser();
		//上传文件
		for (MultipartFile multipartFile : files) {
			InputStream inputStream = multipartFile.getInputStream();
			String name = multipartFile.getName();
			if(StringUtils.isBlank(name)){
				continue;
			}
			String substring = name.substring(name.lastIndexOf("."));
			String path = YH_PATH+CommonUtil.getYear()+"/"+puser;
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			String date2Str = DateUtils.date2Str(new Date(), "yyyyMMddhhmmssSSS");
			String filepath=path+"/"+date2Str+substring;
			File destination=new File(filepath);
			FileUtils.copyInputStreamToFile(inputStream, destination);
			
			WEFile weFile = new WEFile(name, filepath, puser, filetime);
			String maxFileid = ifileservice.maxFileid();
			String nextIntValue = CommonUtil.nextIntValue(maxFileid);
			weFile.setFileid(nextIntValue);
			ifileservice.insert(weFile);
			w.addpurl(nextIntValue);
		}
		w.setPtype(WEProblem.PTYPE_YH);
		w.setPstate(WEProblem.PSTATE_CJ);
		iproblemservice.insert(w);
		return list(puser,WEProblem.PTYPE_YH);
	}
}
