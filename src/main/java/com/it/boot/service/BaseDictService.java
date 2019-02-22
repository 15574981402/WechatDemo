package com.it.boot.service;

import java.util.List;

import com.it.boot.pojo.BaseDict;

public interface BaseDictService {

	List<BaseDict> getDictListByTypeCode(String typeCode);
}
