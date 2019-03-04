package cn.yckj.entity;

public class WEUser {
	/*openid	nvarchar2(100)	n			微信号
	username	nvarchar2(50)	y			用户名
	password	nvarchar2(50)	y			密码
	name	nvarchar2(50)	y			姓名
	phone	nvarchar2(50)	y			电话
	unit	nvarchar2(100)	y			组织机构
	systemtype	nvarchar2(5)	y			所选系统编号
*/
	private String openid;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String unit;
	private String systemtype;
	private String usertime;
	
	//补充
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUsertime() {
		return usertime;
	}
	public void setUsertime(String usertime) {
		this.usertime = usertime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	
}
