package cn.yckj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yckj.entity.WESystem;
@Repository
public interface IWESystemDao {

	List<WESystem> list();

}
