package cn.yckj.service;

import cn.yckj.entity.WEFile;

public interface IFileService {


	String maxFileid();

	Integer insert(WEFile weFile);

}
