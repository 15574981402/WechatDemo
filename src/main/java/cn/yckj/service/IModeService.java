package cn.yckj.service;

import java.util.List;

import cn.yckj.entity.WEMode;

public interface IModeService {

	List<WEMode> list(String systemtype);
}
