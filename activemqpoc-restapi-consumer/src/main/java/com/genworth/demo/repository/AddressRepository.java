package com.genworth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genworth.demo.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	

}