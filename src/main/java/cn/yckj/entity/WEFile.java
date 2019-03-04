package cn.yckj.entity;

public class WEFile {
	/*fileid	nvarchar2(10)	y			附件编号
	filename	nvarchar2(200)	y			附件原始文件名
	filepath	nvarchar2(300)	y			附件路径
	fileuserid	nvarchar2(80)	y			附件上传人编号
	filetime	nvarchar2(60)	y			附件上传时间*/
	private String fileid;
	private String filename;
	private String filepath;
	private String fileuserid;
	private String filetime;
	
	public WEFile(String filename, String filepath, String fileuserid, String filetime) {
		super();
		this.filename = filename;
		this.filepath = filepath;
		this.fileuserid = fileuserid;
		this.filetime = filetime;
	}
	public WEFile(){}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFileuserid() {
		return fileuserid;
	}
	public void setFileuserid(String fileuserid) {
		this.fileuserid = fileuserid;
	}
	public String getFiletime() {
		return filetime;
	}
	public void setFiletime(String filetime) {
		this.filetime = filetime;
	}
	

}
