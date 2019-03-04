package cn.yckj.service;

import cn.yckj.entity.WEUser;

public interface IUserService {
	String findSystemtype(String openid);

	Integer insert(WEUser u);

	WEUser findbyopenid(String openid);

	WEUser login(String username, String password);


	void updateOpenid(String openid, String username, String password);

	boolean exist(String openid);
}
