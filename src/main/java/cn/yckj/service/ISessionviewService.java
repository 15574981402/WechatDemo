package cn.yckj.service;

import java.util.List;

import cn.yckj.entity.WESessionview;

public interface ISessionviewService {

	Integer insert(WESessionview weSessionview);

	String nextIntSvid(String stateOrSid);

	List<WESessionview> list(String sid);

}
