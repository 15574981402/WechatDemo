package com.it.boot.service;

import com.it.boot.pojo.Customer;
import com.it.boot.pojo.QueryVo;
import com.it.boot.util.Page;

public interface CustomerService {

	Page<Customer> getCustomerList(QueryVo queryVo);
	Customer getCustomerById(Long id);
	void updateCustomer(Customer customer);
	void deleteCustomer(Long id);
}
