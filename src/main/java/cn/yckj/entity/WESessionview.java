package cn.yckj.entity;

import org.springframework.web.multipart.MultipartFile;

public class WESessionview {

	public static final String AREMAKE_RECEIVE="receive";
	public static final String AREMAKE_REPLY="reply";
/*	svid	varchar2(10)	n			会话详细编号
	sid	varchar2(10)	y			会话编号
	stime	varchar2(30)	y			会话时间
	sendid	varchar2(60)	y			发送人
	reviewid	varchar2(60)	y			接收人
	msgtype	varchar2(20)	y			消息类型
	msgcontent	varchar2(40)	y			消息内容
	aremake	nvarchar2(50)	y			备用字段a
	bremake	nvarchar2(50)	y			备用字段b
	cremaker	nvarchar2(50)	y			备用字段c*/
	public WESessionview(String svid, String sid, String stime, String sendid, String reviewid, String msgtype,
			String msgcontent,String aremake ) {
		super();
		this.svid = svid;
		this.sid = sid;
		this.stime = stime;
		this.sendid = sendid;
		this.reviewid = reviewid;
		this.msgtype = msgtype;
		this.msgcontent = msgcontent;
		this.aremake=aremake;
	}
	public WESessionview(){}
	private String svid;
	
	private String sid;
	private String stime;
	private String sendid;
	private String reviewid;
	private String msgtype;
	private String msgcontent;
	private String aremake;
	private String bremake;
	private String cremaker;
	
	
	public String getSvid() {
		return svid;
	}
	public void setSvid(String svid) {
		this.svid = svid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getSendid() {
		return sendid;
	}
	public void setSendid(String sendid) {
		this.sendid = sendid;
	}
	public String getReviewid() {
		return reviewid;
	}
	public void setReviewid(String reviewid) {
		this.reviewid = reviewid;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	public String getAremake() {
		return aremake;
	}
	public void setAremake(String aremake) {
		this.aremake = aremake;
	}
	public String getBremake() {
		return bremake;
	}
	public void setBremake(String bremake) {
		this.bremake = bremake;
	}
	public String getCremaker() {
		return cremaker;
	}
	public void setCremaker(String cremaker) {
		this.cremaker = cremaker;
	}
	@Override
	public String toString() {
		return "WESessionview [svid=" + svid + ", sid=" + sid + ", stime=" + stime + ", sendid=" + sendid
				+ ", reviewid=" + reviewid + ", msgtype=" + msgtype + ", msgcontent=" + msgcontent + ", aremake="
				+ aremake + "]";
	}
}
