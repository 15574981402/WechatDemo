package cn.yckj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yckj.entity.WEProblem;

@Repository
public interface IProblemDao {

	Integer insert(WEProblem w);

	List<WEProblem> list(@Param("puser")String puser, @Param("ptype")String ptype);

	String maxPid();

}
