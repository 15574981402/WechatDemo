package cn.yckj.service;

import java.util.List;

import cn.yckj.entity.WEProblem;

public interface IProblemService {

	Integer insert(WEProblem w);

	List<WEProblem> list(String puser, String ptype);

	String maxPid();

}
