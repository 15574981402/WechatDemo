package cn.yckj.service;

import java.util.List;

import cn.yckj.entity.WESession;

public interface ISessionService {
	Integer insert(WESession s);

	String nextIntID();

	void updateState(String stateOrSid, String string);

	List<WESession> listByCid(String cid);

	WESession findByid(String sid);
}
