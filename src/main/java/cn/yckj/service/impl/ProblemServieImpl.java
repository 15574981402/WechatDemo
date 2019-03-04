package cn.yckj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.IProblemDao;
import cn.yckj.entity.WEProblem;
import cn.yckj.service.IProblemService;

@Service
public class ProblemServieImpl implements IProblemService {
	@Autowired
	private IProblemDao iproblemdao;
	@Override
	public Integer insert(WEProblem w) {
		return iproblemdao.insert(w);
	}
	@Override
	public List<WEProblem> list(String puser, String ptype) {
		return iproblemdao.list(puser,ptype);
	}
	@Override
	public String maxPid() {
		return iproblemdao.maxPid();
	}

}
