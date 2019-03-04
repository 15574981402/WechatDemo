package cn.yckj.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yckj.entity.WEUser;
@Repository
public interface IUserDao {

	String findSystemtype(String openid);

	Integer insert(WEUser u);

	WEUser findbyopenid(String openid);

	WEUser login(@Param(value = "username")String username, @Param(value = "password")String password);

	void updateOpenid(@Param(value = "openid")String openid, @Param(value = "username")String username, @Param(value = "password")String password);

	int exist(String openid);

}
