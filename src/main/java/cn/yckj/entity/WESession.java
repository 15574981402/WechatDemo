package cn.yckj.entity;

public class WESession {/*	id	nvarchar2(10)	n			会话编号
	openid	nvarchar2(50)	y			会话微信号
	opentime	nvarchar2(50)	y			会话时间
	cid	nvarchar2(50)	y			客服账号
	cstate	nvarchar2(50)	y			会话状态
	aremake	nvarchar2(50)	y			模块编号
	bremake	nvarchar2(50)	y			备用字段b
	cremaker	nvarchar2(50)	y			备用字段c*/


	private String id;
	private String openid;
	private String opentime;
	private String cid;
	private String cstate;
	private String aremake;

	public WESession(String id, String openid, String opentime, String cid, String cstate, String moduleid) {
		super();
		this.id = id;
		this.openid = openid;
		this.opentime = opentime;
		this.cid = cid;
		this.cstate = cstate;
		this.aremake=moduleid;
	}
	public WESession(){}

	public WESession(String openid) {
		this.openid=openid;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCstate() {
		return cstate;
	}

	public void setCstate(String cstate) {
		this.cstate = cstate;
	}

	public String getAremake() {
		return aremake;
	}

	public void setAremake(String aremake) {
		this.aremake = aremake;
	}


}
