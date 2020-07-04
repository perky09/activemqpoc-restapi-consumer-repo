package com.genworth.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genworth.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public List<Customer> findByName(String name);
	
	
	
	

}
