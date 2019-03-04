package cn.yckj.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.ISessionviewDao;
import cn.yckj.entity.WESessionview;
import cn.yckj.service.ISessionviewService;
@Service
public class SessionviewSerImp implements ISessionviewService {
	@Autowired
	private ISessionviewDao isessionviewdao;

	@Override
	public Integer insert(WESessionview weSessionview) {
		return isessionviewdao.insert(weSessionview);
	}

	@Override
	public String nextIntSvid(String stateOrSid) {
		String maxIntSvid = isessionviewdao.maxIntSvid(stateOrSid);
		if (StringUtils.isBlank(maxIntSvid)) {
			return "1";
		}
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(maxIntSvid);
		} catch (Exception e) {
			return "1";
		}
		return parseInt + 1 + "";
	}

	@Override
	public List<WESessionview> list(String sid) {
		return isessionviewdao.list(sid);
	}
}
