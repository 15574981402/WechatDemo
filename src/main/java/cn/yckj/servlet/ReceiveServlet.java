package cn.yckj.servlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import cn.yckj.entity.WEAutoreply;
import cn.yckj.entity.WECustom;
import cn.yckj.entity.WEMode;
import cn.yckj.entity.WESession;
import cn.yckj.entity.WESessionview;
import cn.yckj.entity.WESystem;
import cn.yckj.service.IAutoreplyService;
import cn.yckj.service.ICustomService;
import cn.yckj.service.IModeService;
import cn.yckj.service.ISessionService;
import cn.yckj.service.ISessionviewService;
import cn.yckj.service.IUserService;
import cn.yckj.service.IWESystemService;
import cn.yckj.service.impl.AutoreplyServiceImpl;
import cn.yckj.service.impl.CustomServiceImpl;
import cn.yckj.service.impl.ModeSeriviceImpl;
import cn.yckj.service.impl.SessionServiceImpl;
import cn.yckj.service.impl.SessionviewSerImp;
import cn.yckj.service.impl.UserSeriviceImpl;
import cn.yckj.service.impl.WESystemServiceImpl;
import cn.yckj.utils.CommonUtil;
import cn.yckj.utils.DateUtils;
import weixin.popular.api.MediaAPI;
import weixin.popular.bean.media.MediaGetResult;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.TokenManager;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

/**
 * 服务端事件消息接收
 * 
 * @author Yi
 *
 */
@Controller
public class ReceiveServlet extends HttpServlet {

	/** 客服 */
	private static final String MENU_KF = "kf";
	/** 常见问题 */
	private static final String MENU_HF = "hf";
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// @Autowired
	// private ICustomService icustomservice;
	// @Autowired
	// private ISessionService isessionservice;
	// @Autowired
	// private ISessionviewService isessionviewservice;
	private static ICustomService icustomservice = SpringContextUtil.getBean(CustomServiceImpl.class);
	private static ISessionService isessionservice = SpringContextUtil.getBean(SessionServiceImpl.class);
	private static ISessionviewService isessionviewservice = SpringContextUtil.getBean(SessionviewSerImp.class);
	private static IUserService iuserservice = SpringContextUtil.getBean(UserSeriviceImpl.class);
	private static IModeService imodeservice = SpringContextUtil.getBean(ModeSeriviceImpl.class);
	private static IAutoreplyService iautoreplyservice = SpringContextUtil.getBean(AutoreplyServiceImpl.class);
	private static IWESystemService systemservice = SpringContextUtil.getBean(WESystemServiceImpl.class);
	static{
		 icustomservice = SpringContextUtil.getBean(CustomServiceImpl.class);
		 isessionservice = SpringContextUtil.getBean(SessionServiceImpl.class);
		 isessionviewservice = SpringContextUtil.getBean(SessionviewSerImp.class);
		 iuserservice = SpringContextUtil.getBean(UserSeriviceImpl.class); 
		 imodeservice = SpringContextUtil.getBean(ModeSeriviceImpl.class);
		 iautoreplyservice = SpringContextUtil.getBean(AutoreplyServiceImpl.class);
		 systemservice = SpringContextUtil.getBean(WESystemServiceImpl.class);
	}
	// 从官方获取
	private String token = "test";
	private String FILE_PATH="D:\\IDE-workspace\\wechat\\cn.yckj.crm\\src\\main\\webapp/";

	// 重复通知过滤
	private static ExpireKey expireKey = new DefaultExpireKey();
	private static Map<String, String> openIdMap = new ConcurrentHashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream();
		ServletOutputStream outputStream = response.getOutputStream();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		// 首次请求申请验证,返回echostr
		if (echostr != null) {
			outputStreamWrite(outputStream, echostr);
			return;
		}

		// 验证请求签名
		if (!signature.equals(SignatureUtil.generateEventMessageSignature(token, timestamp, nonce))) {
			System.out.println("The request signature is invalid");
			return;
		}

		if (inputStream != null) {
			// 转换XML
			EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
			String key = eventMessage.getFromUserName() + "__" + eventMessage.getToUserName() + "__"
					+ eventMessage.getMsgId() + "__" + eventMessage.getCreateTime();
			if (expireKey.exists(key)) {
				// 重复通知不作处理
				return;
			} else {
				expireKey.add(key);
			}

			String msgType = eventMessage.getMsgType();
			String openid = eventMessage.getFromUserName();
			XMLMessage xmlTextMessage = null;
			if ("event".equals(msgType)) {
				if ("subscribe".equals(eventMessage.getEvent())) { // 关注事件
					// 创建回复
					xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(),
							"yckj公众号欢迎您!");
					xmlTextMessage.outputStreamWrite(outputStream);
					return;
				} else {
					if ("key_hf".equals(eventMessage.getEventKey())) {
						xmlTextMessage = hfMenu(eventMessage, openid);
						xmlTextMessage.outputStreamWrite(outputStream);
						return;
					} else if ("key_kf".equals(eventMessage.getEventKey())) {
						xmlTextMessage = kfMenu(eventMessage, openid);
						xmlTextMessage.outputStreamWrite(outputStream);
						return;
					} else {
						xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
								eventMessage.getToUserName(), "其他按钮暂未处理");
						xmlTextMessage.outputStreamWrite(outputStream);
						return;
					}
				}
			} else {
				String customid_state = openIdMap.get(openid);
				if (StringUtils.isNotBlank(customid_state)) {// 判断是否接入过此会话
					String[] ss = customid_state.split("#");
					String customid = ss[0];
					String stateOrSid = ss[1];
					String menuType = ss[2];
					String systemType ="";
					if(ss.length>3){
						 systemType = ss[3];
					}
//					switch (msgType) {
//					case "text":
//						String content = eventMessage.getContent();
//						List<WESystem> sList = systemservice.list();
//						// 判断自动回复时 选择系统编号
//						for (WESystem weSystem : sList) {
//							String systemtype = weSystem.getSystemtype();
//							if (systemtype.equals(content)) {
//								if (MENU_HF.equals(menuType)) {
//									xmlTextMessage = autoreplyBysystemtype(eventMessage, openid, systemtype);
//									xmlTextMessage.outputStreamWrite(outputStream);
//									return;
//								}
//							}
//						}
//						// 判断选择系统模块后 客服模块必携带systemType
//						if (StringUtils.isNotBlank(systemType)) {
//							List<WEMode> mList = imodeservice.list(systemType);
//							for (WEMode weMode : mList) {
//								String moduleid = weMode.getModuleid();
//								if (moduleid.equals(content)) {
//									if ("创建".equals(stateOrSid)) {
//										String nextIntID = isessionservice.nextIntID();
//										isessionservice.insert(new WESession(nextIntID, openid, CommonUtil.getDate(),
//												customid, "创建",moduleid));
//										openIdMap.put(openid,
//												customid + "#" + nextIntID + "#" + menuType + "#" + systemType);
//										xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
//												eventMessage.getToUserName(),
//												"人工客服已接通,我是客服编号[" + customid + "],请问有什么可以帮您?");
//										xmlTextMessage.outputStreamWrite(outputStream);
//										return;
//									} 
//								}
//							}
//						}
//						if(MENU_KF.equals(menuType)&&StringUtils.isNotBlank(systemType)&&(!"创建".equals(stateOrSid))){
//								isessionservice.updateState(stateOrSid, "会话");
//								isessionviewservice.insert(new WESessionview(
//										isessionviewservice.nextIntSvid(stateOrSid), stateOrSid,
//										CommonUtil.getDate(), openid, customid, "text", content,WESessionview.AREMAKE_RECEIVE));
//								return;
//						}
//						break;
//					case "image":
//						if(MENU_KF.equals(menuType)&&StringUtils.isNotBlank(systemType)){
//							String mediaId = eventMessage.getMediaId();
//							MediaGetResult mediaGet = MediaAPI.mediaGet(TokenManager.getDefaultToken(), mediaId);
////							String contentType = mediaGet.getContentType();
//							String filename = mediaGet.getFilename();
//							String substring = filename.substring(filename.lastIndexOf("."));
//							String path="img/"+ Calendar.getInstance().get(Calendar.YEAR)+"/"+openid;
//							String pathname = FILE_PATH+path;
//							File file = new File(pathname);
//							if(!file.exists()){
//								file.mkdirs();
//							}
//							String date2Str = DateUtils.date2Str(new Date(), "yyyyMMddhhmmssSSS");
//							String img=path+"/"+date2Str+substring;
//							FileUtils.copyInputStreamToFile(new ByteArrayInputStream(mediaGet.getBytes()) , new File(img));
//							
//							isessionservice.updateState(stateOrSid, "会话");
//							isessionviewservice.insert(new WESessionview(
//									isessionviewservice.nextIntSvid(stateOrSid), stateOrSid,
//									CommonUtil.getDate(), openid, customid, "image", img,WESessionview.AREMAKE_RECEIVE));
//							return;
//						}else{
//							XMLMessage xmlTextMessage1 = new XMLTextMessage(eventMessage.getFromUserName(),
//									eventMessage.getToUserName(), "图片消息只支持客服会话中发送!!!,否则不进行处理!!");
//							xmlTextMessage1.outputStreamWrite(outputStream);
//							return;
//						}
//					case "voice":
//						if(MENU_KF.equals(menuType)&&StringUtils.isNotBlank(systemType)){
//							String mediaId = eventMessage.getMediaId();
//							MediaGetResult mediaGet = MediaAPI.mediaGet(TokenManager.getDefaultToken(), mediaId);
////							String contentType = mediaGet.getContentType();
//							String filename = mediaGet.getFilename();
//							String substring = filename.substring(filename.lastIndexOf("."));
//							String path="voice/"+Calendar.getInstance().get(Calendar.YEAR)+"/"+openid;
//							String pathname = FILE_PATH+path;
//							File file = new File(pathname);
//							if(!file.exists()){
//								file.mkdirs();
//							}
//							String date2Str = DateUtils.date2Str(new Date(), "yyyyMMddhhmmssSSS");
//							String voice=path+"/"+date2Str+substring;
//							FileUtils.copyInputStreamToFile(new ByteArrayInputStream(mediaGet.getBytes()) , new File(voice));
//							isessionservice.updateState(stateOrSid, "会话");
//							isessionviewservice.insert(new WESessionview(
//									isessionviewservice.nextIntSvid(stateOrSid), stateOrSid,
//									CommonUtil.getDate(), openid, customid, "voice", voice,WESessionview.AREMAKE_RECEIVE));
//							return;
//						}else{
//							XMLMessage xmlTextMessage1 = new XMLTextMessage(eventMessage.getFromUserName(),
//									eventMessage.getToUserName(), "语音消息只支持客服会话中发送!!!,否则不进行处理!!");
//							xmlTextMessage1.outputStreamWrite(outputStream);
//							return;
//						}
//					case "video":
//					case "music":
//					case "news":
//						XMLMessage xmlTextMessage4 = new XMLTextMessage(eventMessage.getFromUserName(),
//								eventMessage.getToUserName(), "[视频,音乐,图文]消息暂未做处理");
//						xmlTextMessage4.outputStreamWrite(outputStream);
//						break;
//					default:
//						break;
//					}
					if( "text".equals(msgType)){
						String content = eventMessage.getContent();
						List<WESystem> sList = systemservice.list();
						// 判断自动回复时 选择系统编号
						for (WESystem weSystem : sList) {
							String systemtype = weSystem.getSystemtype();
							if (systemtype.equals(content)) {
								if (MENU_HF.equals(menuType)) {
									xmlTextMessage = autoreplyBysystemtype(eventMessage, openid, systemtype);
									xmlTextMessage.outputStreamWrite(outputStream);
									return;
								}
							}
						}
						// 判断选择系统模块后 客服模块必携带systemType
						if (StringUtils.isNotBlank(systemType)) {
							List<WEMode> mList = imodeservice.list(systemType);
							for (WEMode weMode : mList) {
								String moduleid = weMode.getModuleid();
								if (moduleid.equals(content)) {
									if ("创建".equals(stateOrSid)) {
										String nextIntID = isessionservice.nextIntID();
										isessionservice.insert(new WESession(nextIntID, openid, CommonUtil.getDate(),
												customid, "创建",moduleid));
										openIdMap.put(openid,
												customid + "#" + nextIntID + "#" + menuType + "#" + systemType);
										xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
												eventMessage.getToUserName(),
												"人工客服已接通,我是客服编号[" + customid + "],请问有什么可以帮您?");
										xmlTextMessage.outputStreamWrite(outputStream);
										return;
									} 
								}
							}
						}
						if(MENU_KF.equals(menuType)&&StringUtils.isNotBlank(systemType)&&(!"创建".equals(stateOrSid))){
								isessionservice.updateState(stateOrSid, "会话");
								isessionviewservice.insert(new WESessionview(
										isessionviewservice.nextIntSvid(stateOrSid), stateOrSid,
										CommonUtil.getDate(), openid, customid, "text", content,WESessionview.AREMAKE_RECEIVE));
								return;
						}
					}else if("image".equals(msgType)){
						if(MENU_KF.equals(menuType)&&StringUtils.isNotBlank(systemType)){
							String mediaId = eventMessage.getMediaId();
							MediaGetResult mediaGet = MediaAPI.mediaGet(TokenManager.getDefaultToken(), mediaId);
//							String contentType = mediaGet.getContentType();
							String filename = mediaGet.getFilename();
							String substring = filename.substring(filename.lastIndexOf("."));
							String path="img/"+ Calendar.getInstance().get(Calendar.YEAR)+"/"+openid;
							String pathname = FILE_PATH+path;
							File file = new File(pathname);
							if(!file.exists()){
								file.mkdirs();
							}
							String date2Str = DateUtils.date2Str(new Date(), "yyyyMMddhhmmssSSS");
							String img=path+"/"+date2Str+substring;
							FileUtils.copyInputStreamToFile(new ByteArrayInputStream(mediaGet.getBytes()) , new File(img));
							
							isessionservice.updateState(stateOrSid, "会话");
							isessionviewservice.insert(new WESessionview(
							isessionviewservice.nextIntSvid(stateOrSid), stateOrSid,
									CommonUtil.getDate(), openid, customid, "image", img,WESessionview.AREMAKE_RECEIVE));
							return;
						}else{
							XMLMessage xmlTextMessage1 = new XMLTextMessage(eventMessage.getFromUserName(),
									eventMessage.getToUserName(), "图片消息只支持客服会话中发送!!!,否则不进行处理!!");
							xmlTextMessage1.outputStreamWrite(outputStream);
							return;
						}
					}else if("voice".equals(msgType)){
						if(MENU_KF.equals(menuType)&&StringUtils.isNotBlank(systemType)){
							String mediaId = eventMessage.getMediaId();
							MediaGetResult mediaGet = MediaAPI.mediaGet(TokenManager.getDefaultToken(), mediaId);
//							String contentType = mediaGet.getContentType();
							String filename = mediaGet.getFilename();
							String substring = filename.substring(filename.lastIndexOf("."));
							String path="voice/"+Calendar.getInstance().get(Calendar.YEAR)+"/"+openid;
							String pathname = FILE_PATH+path;
							File file = new File(pathname);
							if(!file.exists()){
								file.mkdirs();
							}
							String date2Str = DateUtils.date2Str(new Date(), "yyyyMMddhhmmssSSS");
							String voice=path+"/"+date2Str+substring;
							FileUtils.copyInputStreamToFile(new ByteArrayInputStream(mediaGet.getBytes()) , new File(voice));
							isessionservice.updateState(stateOrSid, "会话");
							isessionviewservice.insert(new WESessionview(
									isessionviewservice.nextIntSvid(stateOrSid), stateOrSid,
									CommonUtil.getDate(), openid, customid, "voice", voice,WESessionview.AREMAKE_RECEIVE));
							return;
						}else{
							XMLMessage xmlTextMessage1 = new XMLTextMessage(eventMessage.getFromUserName(),
									eventMessage.getToUserName(), "语音消息只支持客服会话中发送!!!,否则不进行处理!!");
							xmlTextMessage1.outputStreamWrite(outputStream);
							return;
						}
					}else if("video".equals(msgType) || "music".equals(msgType) || "news".equals(msgType) ){
						XMLMessage xmlTextMessage4 = new XMLTextMessage(eventMessage.getFromUserName(),
								eventMessage.getToUserName(), "[视频,音乐,图文]消息暂未做处理");
						xmlTextMessage4.outputStreamWrite(outputStream);
						return;
					}else{
						return;
					}
				} else {
//					switch (msgType) {
//					case "text":
//					case "image":
//					case "voice":
//						XMLMessage xmlTextMessage1 = new XMLTextMessage(eventMessage.getFromUserName(),
//								eventMessage.getToUserName(), "[文字,图片,语音]消息请于客服会话中发送!!!,否则不进行处理!!");
//						xmlTextMessage1.outputStreamWrite(outputStream);
//						break;
//					case "video":
//					case "music":
//					case "news":
//						XMLMessage xmlTextMessage4 = new XMLTextMessage(eventMessage.getFromUserName(),
//								eventMessage.getToUserName(), "[视频,音乐,图文]消息暂未做处理");
//						xmlTextMessage4.outputStreamWrite(outputStream);
//						break;
//					default:
//						break;
//					}
					if("text".equals(msgType)||"image".equals(msgType)||"voice".equals(msgType)){
						XMLMessage xmlTextMessage1 = new XMLTextMessage(eventMessage.getFromUserName(),
								eventMessage.getToUserName(), "[文字,图片,语音]消息请于客服会话中发送!!!,否则不进行处理!!");
						xmlTextMessage1.outputStreamWrite(outputStream);
						return;
					}else if("video".equals(msgType)||"music".equals(msgType)||"news".equals(msgType)){
						XMLMessage xmlTextMessage4 = new XMLTextMessage(eventMessage.getFromUserName(),
								eventMessage.getToUserName(), "[视频,音乐,图文]消息暂未做处理");
						xmlTextMessage4.outputStreamWrite(outputStream);
						return;
					}else{
						return;
					}
				}
			}
			return;
		}
	}

	private XMLMessage hfMenu(EventMessage eventMessage, String openid) {
		String findSystemtype = iuserservice.findSystemtype(openid);
		return autoreplyBysystemtype(eventMessage, openid, findSystemtype);
	}

	private XMLMessage autoreplyBysystemtype(EventMessage eventMessage, String openid, String findSystemtype) {
		XMLMessage xmlTextMessage = null;
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(findSystemtype)) {
			openIdMap.put(openid, "#创建#" + MENU_HF + "#" + findSystemtype);
			List<WEAutoreply> aList = iautoreplyservice.list(findSystemtype);
			sb.append("请点击对应的系统问题:\n");
			for (WEAutoreply a : aList) {
				sb.append("<a href='").append(a.getUrl()).append("' >");
				sb.append("[").append(a.getSystemid()).append("] ").append(a.getAutocontent());
				sb.append("</a>").append("\n");
			}

		} else {
			openIdMap.put(openid, "#创建#" + MENU_HF + "#");
			List<WESystem> sList = systemservice.list();
			sb.append("请选择自动回复的系统:\n");
			for (WESystem weSystem : sList) {
				sb.append("[").append(weSystem.getSystemtype()).append("] ").append(weSystem.getSystemname())
						.append("\n");
			}
		}
		xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(),
				sb.toString());
		return xmlTextMessage;
	}

	private XMLMessage kfMenu(EventMessage eventMessage, String openid) {
		XMLMessage xmlTextMessage = null;
		List<WECustom> list = null;
		try {
			list = icustomservice.list("在线");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null || list.isEmpty()) {
			xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(),
					"很抱歉,客服人员暂未在线,工作时间:工作日:08:30-12:00以及13:30-18:00,或点击[服务]-[常见问题]了解更多");
		} else {
			String findSystemtype = iuserservice.findSystemtype(openid);
			Random r = new Random();
			if (StringUtils.isNotBlank(findSystemtype)) {
				openIdMap.put(openid,
						list.get(r.nextInt(list.size())).getCustomid() + "#创建#" + MENU_KF + "#" + findSystemtype);
				List<WEMode> mList = imodeservice.list(findSystemtype);
				StringBuilder sb = new StringBuilder();
				sb.append("请输入序号选择对应的模块:\n");
				for (WEMode weMode : mList) {
					sb.append("[").append(weMode.getModuleid()).append("] ").append(weMode.getModulename())
							.append("\n");
				}
				xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(),
						sb.toString());

			} else {
				xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(),
						"用户请先进行注册,点击[扩展业务]-[注册/绑定]");
			}
		}
		return xmlTextMessage;
	}

	/**
	 * 数据流输出
	 * 
	 * @param outputStream
	 * @param text
	 * @return
	 */
	private boolean outputStreamWrite(OutputStream outputStream, String text) {
		try {
			outputStream.write(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
