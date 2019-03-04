package cn.yckj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yckj.entity.WESession;
@Repository
public interface ISessionDao {


	Integer insert(WESession s);

	String maxId();

	void updateState(@Param(value = "id")String id, @Param(value = "cstate")String cstate);

	List<WESession> listByCid(String cid);

	WESession findByid(String sid);

}
