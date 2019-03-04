package cn.yckj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yckj.entity.WESessionview;

@Repository
public interface ISessionviewDao {

	Integer insert(WESessionview weSessionview);

	String maxIntSvid(String stateOrSid);

	List<WESessionview> list(String sid);

}
