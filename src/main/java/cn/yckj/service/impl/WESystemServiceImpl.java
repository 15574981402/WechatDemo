package cn.yckj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.IWESystemDao;
import cn.yckj.entity.WESystem;
import cn.yckj.service.IWESystemService;
@Service
public class WESystemServiceImpl implements IWESystemService {
	@Autowired
	private IWESystemDao iwesystemdao;
	@Override
	public List<WESystem> list() {
		return iwesystemdao.list();
	}
	
}
