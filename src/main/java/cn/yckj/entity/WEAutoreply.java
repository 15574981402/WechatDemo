package cn.yckj.entity;

public class WEAutoreply {
/*	systemtype	nvarchar2(10)	y			系统类型
	systemid	nvarchar2(10)	n			系统编号
	url	nvarchar2(200)	y			问题解决页面地址
	autocontent	nvarchar2(300)	y			问题提示内容*/
	private String systemtype;
	private String systemid;
	private String url;
	private String autocontent;
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public String getSystemid() {
		return systemid;
	}
	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAutocontent() {
		return autocontent;
	}
	public void setAutocontent(String autocontent) {
		this.autocontent = autocontent;
	}

}
