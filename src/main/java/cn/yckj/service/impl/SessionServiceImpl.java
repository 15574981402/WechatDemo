package cn.yckj.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.ISessionDao;
import cn.yckj.entity.WESession;
import cn.yckj.service.ISessionService;

@Service
public class SessionServiceImpl  implements ISessionService{
	@Autowired
	private ISessionDao isessiondao;
	@Override
	public Integer insert(WESession s) {
		return isessiondao.insert(s);
	}
	@Override
	public String nextIntID() {
		String maxid=isessiondao.maxId();
		if (StringUtils.isBlank(maxid)) {
			return "1";
		}
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(maxid);
		} catch (Exception e) {
			return "1";
		}
		return parseInt + 1 + "";
	}
	@Override
	public void updateState(String id, String cstate) {
		isessiondao.updateState(id,cstate);
	}
	@Override
	public List<WESession> listByCid(String cid) {
		return isessiondao.listByCid(cid);
	}
	@Override
	public WESession findByid(String sid) {
		return isessiondao.findByid(sid);
	}

}
