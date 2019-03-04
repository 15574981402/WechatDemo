package cn.yckj.service;

import java.util.List;

import cn.yckj.entity.WECustom;

public interface ICustomService {
	List<WECustom> list(String cstate)throws Exception;

	WECustom loginin(String cname, String cpassword);

	Integer upcstate(String cstate, String customid);
}
