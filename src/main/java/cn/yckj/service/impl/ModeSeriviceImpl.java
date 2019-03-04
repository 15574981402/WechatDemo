package cn.yckj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yckj.dao.IModeDao;
import cn.yckj.entity.WEMode;
import cn.yckj.service.IModeService;
@Service
public class ModeSeriviceImpl  implements IModeService{
	@Autowired
	private IModeDao imodedao;

	@Override
	public List<WEMode> list(String systemtype) {
		return imodedao.list(systemtype);
	}
}
