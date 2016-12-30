package com.robsonrocha.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.robsonrocha.dao.CustomerDAO;
import com.robsonrocha.entity.Customer;
import com.robsonrocha.util.JPADAO;

@Stateless
public class CustomerService {

	@Inject
	@JPADAO
	private CustomerDAO customerDAO;

	public List<Customer> loadAll() {
		return customerDAO.loadAll();
	}
	
	public void create(Customer c) {
		customerDAO.create(c);
	}
	
	public void update(Customer c) {
		customerDAO.update(c);
	}
	
	public void delete(Customer c) {
		customerDAO.delete(c);
	}
}
