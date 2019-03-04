package cn.yckj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.IUserDao;
import cn.yckj.entity.WEUser;
import cn.yckj.service.IUserService;
@Service
public class UserSeriviceImpl implements IUserService {
	@Autowired
	private IUserDao iuserdao;
	@Override
	public String findSystemtype(String openid) {
		return iuserdao.findSystemtype(openid);
	}
	@Override
	public Integer insert(WEUser u) {
		
		return iuserdao.insert(u);
	}
	@Override
	public WEUser findbyopenid(String openid) {
		return iuserdao.findbyopenid(openid);
	}
	@Override
	public WEUser login(String username, String password) {
		return iuserdao.login(username,password);
	}
	@Override
	public void updateOpenid(String openid, String username, String password) {
		iuserdao.updateOpenid(openid,username,password);
	}
	@Override
	public boolean exist(String openid) {
		int count= iuserdao.exist(openid);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	
}
