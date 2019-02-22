package com.it.boot.dao;

import java.util.List;

import com.it.boot.pojo.BaseDict;

public interface BaseDictDao {

	List<BaseDict> getDictListByTypeCode(String typeCode);
}
