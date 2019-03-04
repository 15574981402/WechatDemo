package cn.yckj.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.yckj.entity.WECustom;
import cn.yckj.entity.WESession;
import cn.yckj.entity.WESessionview;
import cn.yckj.service.ICustomService;
import cn.yckj.service.ISessionService;
import cn.yckj.service.ISessionviewService;
import cn.yckj.utils.CommonUtil;
import cn.yckj.utils.DateUtils;
import weixin.popular.api.MediaAPI;
import weixin.popular.api.MessageAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.media.Media;
import weixin.popular.bean.media.MediaType;
import weixin.popular.bean.message.message.ImageMessage;
import weixin.popular.bean.message.message.TextMessage;
import weixin.popular.support.TokenManager;

@Controller
public class CustomAction {
	@Value("${customaction.FILE_PATH}")
	private   String FILE_PATH ;
	@Autowired
	private ISessionService isessionservice;
	@Autowired
	private ISessionviewService isessionviewservice;
	@Autowired
	private ICustomService icustomservice;
	@RequestMapping(value = "/custom/reply", method = RequestMethod.POST,produces="html/text;charset=utf-8")
	@ResponseBody
	public String reply(@RequestParam("sid") String sid,@RequestParam("msgcontent") String msgcontent, @RequestParam("file") MultipartFile file) throws Exception {
//		WESessionview e,
		WESessionview e = new WESessionview();
//		String sid = e.getSid();
		e.setSid(sid);
		e.setMsgcontent(msgcontent);
		System.out.println(sid);
		e.setStime(CommonUtil.getDate());
		e.setAremake(WESessionview.AREMAKE_REPLY);
		WESession session = isessionservice.findByid(sid);
		e.setSendid(session.getCid());
		e.setReviewid(session.getOpenid());
		e.setMsgtype("text");
//		String msgcontent = e.getMsgcontent();
		if (StringUtils.isNotBlank(msgcontent)) {
			TextMessage message = new TextMessage(session.getOpenid(), msgcontent);
			BaseResult messageCustomSend = MessageAPI.messageCustomSend(TokenManager.getDefaultToken(), message);
			boolean success = messageCustomSend.isSuccess();
			if (success) {
				e.setSvid(isessionviewservice.nextIntSvid(sid));
				Integer insert = isessionviewservice.insert(e);
			}
		}
		if (file == null || StringUtils.isBlank(file.getOriginalFilename())) {
			System.out.println("未接收到文件");
		} else {
			e.setMsgtype("image");
			String path = "img/" + Calendar.getInstance().get(Calendar.YEAR) + "/" + session.getCid();
			String pathname = FILE_PATH + path;
			File img = new File(pathname);
			if (!img.exists()) {
				img.mkdirs();
			}
			System.out.println(file.getContentType()); // 如 image/png
			String filename = file.getOriginalFilename();
			String substring = filename.substring(filename.lastIndexOf("."));
			String date2Str = DateUtils.date2Str(new Date(), "yyyyMMddhhmmssSSS");
			String imgpath = path + "/" + date2Str + substring;
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(pathname+"/" + date2Str + substring));
			FileInputStream fileinput=new FileInputStream(pathname+"/" + date2Str + substring);
			if(fileinput!=null){
				Media mediaUpload = MediaAPI.mediaUpload(TokenManager.getDefaultToken(), MediaType.image,
						fileinput);
				fileinput.close();
				if (mediaUpload.isSuccess()) {
					String media_id = mediaUpload.getMedia_id();
					BaseResult imgResult = MessageAPI.messageCustomSend(TokenManager.getDefaultToken(),
							new ImageMessage(session.getOpenid(), media_id));
					if (imgResult.isSuccess()) {
						e.setSvid(isessionviewservice.nextIntSvid(sid));
						
//						MediaGetResult mediaGet = MediaAPI.mediaGet(TokenManager.getDefaultToken(), media_id);
//						byte[] bytes = mediaGet.getBytes();
//						System.out.println(Arrays.toString(bytes));
//						FileUtils.copyInputStreamToFile(new ByteArrayInputStream(bytes), new File(imgpath));
						e.setMsgcontent(imgpath);
						isessionviewservice.insert(e);
					} else {
						// TODO 图片消息发送失败
						System.out.println("图片消息发送失败");
					}
				} else {
					// 图片回复失败
					System.out.println("图片临时素材发送失败");
				}
			}else{
			}
			
		}
		return JSONObject.toJSONString("OK");
		// }else{
		// return JSONObject.toJSONString("ERRO");
		// }
	}

	@RequestMapping(value = "/custom/list", method = RequestMethod.POST)
	public String list(String sid, Model model) throws Exception {
		List<WESessionview> viewList = isessionviewservice.list(sid);
		model.addAttribute("viewList", viewList);
		return "session/sessionview";
	}

	@RequestMapping(value = "/custom/loginin", method = RequestMethod.POST)
	public ModelAndView loginin(WECustom c, Model model) throws Exception {
		WECustom i = icustomservice.loginin(c.getCname(), c.getCpassword());
		if (i == null) {
			return new ModelAndView("custom/loginin", "msg", "用户名或密码不正确");
		}
		List<WESession> slist = isessionservice.listByCid(i.getCustomid());
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("slist", slist);
		hashMap.put("custom", i);
		return new ModelAndView("session/sessionview", "data", hashMap);
	}

	@RequestMapping(value = "/custom/loginin", method = RequestMethod.GET)
	public String loginin() throws Exception {
		return "/custom/loginin";
	}
	
	@RequestMapping(value = "/custom/upcstate", method = RequestMethod.POST)
	@ResponseBody
	public String upcstate(@RequestParam("cstate")String cstate,@RequestParam("customid")String customid) throws Exception {
		//todo
		Integer i=icustomservice.upcstate(cstate,customid);
		if(i>0){
			return JSONObject.toJSONString("true");
		}else{
			return JSONObject.toJSONString("false");
		}
	}
}
