package cn.yckj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.IFileDao;
import cn.yckj.entity.WEFile;
import cn.yckj.service.IFileService;

@Service
public class FileServiceImpl  implements IFileService{
	@Autowired
	private IFileDao ifiledao;
	@Override
	public String maxFileid() {
		return ifiledao.maxFileid();
	}

	@Override
	public Integer insert(WEFile weFile) {
		return ifiledao.insert(weFile);
	}
	
}
