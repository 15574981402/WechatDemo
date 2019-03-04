package cn.yckj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yckj.entity.WEAutoreply;

@Repository
public interface IAutoreplyDao {

	List<WEAutoreply> list(String systemtype);

	String maxSystemid(String systemtype);

	void insert(WEAutoreply w);

	void update(WEAutoreply w);

}
