package cn.yckj.entity;

public class WESystem {
	//SYSTEMTYPE	NVARCHAR2(10)	Y			系统编号
	//SYSTEMNAME	NVARCHAR2(50)	Y			系统名称

	private String systemtype;
	private String systemname;
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public String getSystemname() {
		return systemname;
	}
	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}
}
