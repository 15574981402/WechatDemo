package cn.yckj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.IAutoreplyDao;
import cn.yckj.entity.WEAutoreply;
import cn.yckj.service.IAutoreplyService;
@Service("autoreplyservice")
public class AutoreplyServiceImpl implements IAutoreplyService {
	@Autowired
	private IAutoreplyDao iautoreplydao;
	
	@Override
	public List<WEAutoreply> list(String systemtype) {
		return iautoreplydao.list(systemtype);
	}

	@Override
	public String maxSystemid(String systemtype) {
		return iautoreplydao.maxSystemid(systemtype);
	}

	@Override
	public void insert(WEAutoreply w) {
		iautoreplydao.insert(w);
	}

	@Override
	public void update(WEAutoreply w) {
		iautoreplydao.update(w);
	}

}
