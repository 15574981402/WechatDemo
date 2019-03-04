package cn.yckj.entity;

import org.apache.commons.lang3.StringUtils;

public class WEProblem {
	/*ptype	varchar2(20)	n			问题申报/系统优化
	pid	varchar2(10)	n			问题编号
	pdesc	varchar2(400)	y			问题描述
	pmodule	varchar2(100)	y			问题模块编号
	purl	varchar2(100)	y			问题附件编号
	puser	varchar2(100)	y			问题提出人
	pusertime	varchar2(100)	y			问题提出时间
	pcustom	varchar2(100)	y			问题对接人
	pcustomt	varchar2(100)	y			问题对接时间
	pstate	varchar2(20)	n			问题状态
	systemtype	nvarchar2(50)	y			系统编号
	*/
	public static final String PTYPE_SB="问题申报";
	public static final String PTYPE_YH="系统优化";
	public static final String PSTATE_CJ = "创建";
	private String ptype;
	private String pid;
	private String pmodule;
	private String pdesc;
	private String purl;
	private String puser;
	private String pusertime;
	private String pcustom;
	private String pcustomt;
	private String pstate;
	private String systemtype;
	
	
	/*其他字段 systemname 系统名称
	 * modulename 模块名称
	 * */
	private String systemname;
	private String modulename;
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public String getSystemname() {
		return systemname;
	}
	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPmodule() {
		return pmodule;
	}
	public void setPmodule(String pmodule) {
		this.pmodule = pmodule;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	public String getPuser() {
		return puser;
	}
	public void setPuser(String puser) {
		this.puser = puser;
	}
	public String getPusertime() {
		return pusertime;
	}
	public void setPusertime(String pusertime) {
		this.pusertime = pusertime;
	}
	public String getPcustom() {
		return pcustom;
	}
	public void setPcustom(String pcustom) {
		this.pcustom = pcustom;
	}
	public String getPcustomt() {
		return pcustomt;
	}
	public void setPcustomt(String pcustomt) {
		this.pcustomt = pcustomt;
	}
	public String getPstate() {
		return pstate;
	}
	public void setPstate(String pstate) {
		this.pstate = pstate;
	}
	
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public void addpurl(String addpur) {
		if(StringUtils.isBlank(addpur)){
			return ;
		}
		if(StringUtils.isNotBlank(purl)){
			purl+=("#"+addpur);
		}else{
			this.purl=addpur;
		}
		
	}
	@Override
	public String toString() {
		return "WEProblem [ptype=" + ptype + ", pid=" + pid + ", pmodule=" + pmodule + ", pdesc=" + pdesc + ", purl="
				+ purl + ", puser=" + puser + ", pusertime=" + pusertime + ", pcustom=" + pcustom + ", pcustomt="
				+ pcustomt + ", pstate=" + pstate + ", systemtype=" + systemtype + ", systemname=" + systemname
				+ ", modulename=" + modulename + "]";
	}
	
}
