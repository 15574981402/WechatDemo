package cn.yckj.entity;

public class WECustom {
	/*customid	varchar2(10)	n			客服id
	cname	varchar2(30)	y			客服用户名
	cpassword	nvarchar2(30)	y			客服密码
	isvalid	varchar2(5)	y			"是否有效
	pwdquestion	nvarchar2(150)	y			找回密码问题
	pdwanswer	nvarchar2(150)	y			找回密码答案
	aremake	nvarchar2(50)	y			备用字段a
	bremake	nvarchar2(50)	y			备用字段b
	cremaker	nvarchar2(50)	y			备用字段c
	cstate	nvarchar2(30)	y			客服状态*/
	private String customid;
	private String cname;
	private String cpassword;
	private String isvalid;
	private String pwdquestion;
	private String pdwanswer;
	private String cstate;
	private String aremake;
	private String bremake;
	private String cremaker;
	public String getCustomid() {
		return customid;
	}
	public void setCustomid(String customid) {
		this.customid = customid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getPwdquestion() {
		return pwdquestion;
	}
	public void setPwdquestion(String pwdquestion) {
		this.pwdquestion = pwdquestion;
	}
	public String getPdwanswer() {
		return pdwanswer;
	}
	public void setPdwanswer(String pdwanswer) {
		this.pdwanswer = pdwanswer;
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
		return "WECustom [customid=" + customid + ", cname=" + cname + ", cpassword=" + cpassword + ", isvalid="
				+ isvalid + ", pwdquestion=" + pwdquestion + ", pdwanswer=" + pdwanswer + ", cstate=" + cstate
				+ ", aremake=" + aremake + ", bremake=" + bremake + ", cremaker=" + cremaker + "]";
	}
	

}
