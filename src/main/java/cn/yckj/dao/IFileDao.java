package cn.yckj.dao;

import org.springframework.stereotype.Repository;

import cn.yckj.entity.WEFile;

@Repository
public interface IFileDao {

	String maxFileid();

	Integer insert(WEFile weFile);

}
