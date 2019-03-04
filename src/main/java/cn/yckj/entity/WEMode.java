package cn.yckj.entity;

public class WEMode {
	/*systemtype	nvarchar2(10)	y			系统类型
	moduleid	nvarchar2(10)	n			菜单编号
	modulename	nvarchar2(50)	y			菜单名称*/
	private String systemtype;
	private String moduleid;
	private String modulename;
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

}
