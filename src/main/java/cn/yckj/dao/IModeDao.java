package cn.yckj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yckj.entity.WEMode;

@Repository
public interface IModeDao {

	List<WEMode> list(String systemtype);

}
