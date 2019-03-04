package cn.yckj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yckj.entity.WECustom;
@Repository
public interface ICustomDao {

	List<WECustom> list(String cstate)throws Exception;

	WECustom loginin(@Param(value = "cname")String cname, @Param("cpassword")String cpassword);

	Integer upcstate(@Param(value = "cstate")String cstate, @Param("customid")String customid);

}
