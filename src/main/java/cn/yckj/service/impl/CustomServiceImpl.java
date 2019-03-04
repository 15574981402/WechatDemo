package cn.yckj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.ICustomDao;
import cn.yckj.entity.WECustom;
import cn.yckj.service.ICustomService;
@Service
public class CustomServiceImpl implements ICustomService {
	@Autowired
	private ICustomDao icustomdao;
	
	@Override
	public List<WECustom> list(String cstate) throws Exception {
		return icustomdao.list(cstate);
	}

	@Override
	public WECustom loginin(String cname, String cpassword) {
		return icustomdao.loginin(cname,cpassword);
	}

	@Override
	public Integer upcstate(String cstate, String customid) {
		return icustomdao.upcstate(cstate,customid);
	}

}
