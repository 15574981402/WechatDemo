package com.it.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.boot.dao.CustomerDao;
import com.it.boot.pojo.Customer;
import com.it.boot.pojo.QueryVo;
import com.it.boot.service.CustomerService;
import com.it.boot.util.Page;

/**
 * 客户管理Service
 * <p>Title: CustomerServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Override
	public Page<Customer> getCustomerList(QueryVo queryVo) {
		//计算分页起始记录
		if (queryVo.getPage() != null) {
			queryVo.setStart((queryVo.getPage() - 1) * queryVo.getSize());
		}
		List<Customer> customerList = customerDao.getCustomerList(queryVo);
		//创建一个Page对象
		Page<Customer> page = new Page<Customer>();
		page.setRows(customerList);
		//查询总记录数
		Integer count = customerDao.getCustomerListCount(queryVo);
		page.setTotal(count);
		page.setSize(queryVo.getSize());
		page.setPage(queryVo.getPage());
		//返回结果
		return page;
	}
	@Override
	public Customer getCustomerById(Long id) {
		Customer customer = customerDao.getCustomerById(id);
		return customer;
	}
	@Override
	public void updateCustomer(Customer customer) {
		
		customerDao.updateCustomer(customer);
		
	}
	@Override
	public void deleteCustomer(Long id) {
		customerDao.deleteCustomer(id);
	}

}
