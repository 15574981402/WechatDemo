package cn.yckj.service;

import java.util.List;

import cn.yckj.entity.WEAutoreply;

public interface IAutoreplyService {

	List<WEAutoreply> list(String systemtype);

	String maxSystemid(String systemtype);

	void insert(WEAutoreply w);

	void update(WEAutoreply w);

}
