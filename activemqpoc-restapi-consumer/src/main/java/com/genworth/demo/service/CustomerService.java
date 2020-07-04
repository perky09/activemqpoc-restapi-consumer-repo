package com.genworth.demo.service;

import java.util.List;

import com.genworth.demo.model.Customer;
import com.genworth.demo.model.Occupation;

public interface CustomerService {

	Customer createCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	List<Customer> getAllCustomer();

	Customer getCustomerById(long customerId);

	void deleteCustomer(long id);

	List<Customer> getCustomerByName(String name);
	
	List<Occupation> getCustomerByEarnings(int name);
	
	List<Customer> createAllCustomer(List<Customer> customer);

	List<Object[]> getOccupationByType(String type);

	List<Occupation> getCustomerByEarningsLessThan(int earnings);

	List<Occupation> getCustomerByEarningsEqualsTo(int earnings);

}
