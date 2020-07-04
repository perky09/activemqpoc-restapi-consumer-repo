package com.genworth.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.genworth.demo.exception.CustomedException;
import com.genworth.demo.model.Customer;
import com.genworth.demo.model.Occupation;
import com.genworth.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	static RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/customers")
	public List<Customer> getAllCustomer() {
		List<Customer> list = new ArrayList<>();
		try {
			list = customerService.getAllCustomer();
		} catch (Exception e) {
			throw new CustomedException("Unable to Process getAllCustomer api with Exception :"
					+ " " + e.getMessage());
		}

		return list;
	}

	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable long id) {
		
		Customer customer = new Customer();
		try {
			customer = (customerService.getCustomerById(id));
		}catch(Exception e) {
			throw new CustomedException("Unable to Process getCustomerbyID api with Exception :"
					+ " " + e.getMessage());
		}

		return customer;

	}

	@PostMapping("/customers")
	public HttpStatus createCustomer(@RequestBody Customer customer) {
		
		HttpStatus createStatus = HttpStatus.ACCEPTED;		
		try {
			customerService.createCustomer(customer);
			createStatus = HttpStatus.CREATED;
		}catch(Exception e) {
			createStatus = HttpStatus.BAD_REQUEST;			
		}

		return createStatus;

	}

	@PostMapping("/allCustomers")
	public HttpStatus createAllCustomer(@RequestBody List<Customer> customer) {
		HttpStatus createStatus = HttpStatus.ACCEPTED;
		
		try {
			customerService.createAllCustomer(customer);
			createStatus = HttpStatus.CREATED;
		} catch (Exception e) {
			createStatus = HttpStatus.BAD_REQUEST;
		}
		return createStatus;

	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {

		customer.setId(id);
		return ResponseEntity.ok().body(this.customerService.updateCustomer(customer));

	}

	@DeleteMapping("/customers/{id}")
	public HttpStatus updateCustomer(@PathVariable long id) {

		this.customerService.deleteCustomer(id);
		return HttpStatus.OK;

	}

	@GetMapping("/customers/getByName/{name}")
	public List<Customer> getCustomerByName(@PathVariable String name) {
		List<Customer> list = new ArrayList<>();
		try {
			list = customerService.getCustomerByName(name);
		}catch(Exception e) {
			throw new CustomedException("Unable to Process getCustomerByName api with Exception :"
					+ " " + e.getMessage());
		}
		return list;

	}

	@GetMapping("/customers/getByEarningsGreaterThan/{earnings}")
	public ResponseEntity<List<Occupation>> getCustomerByEarnings(@PathVariable int earnings) {

		return ResponseEntity.ok().body(customerService.getCustomerByEarnings(earnings));

	}

	@GetMapping("/customers/getByEarningsLessThan/{earnings}")
	public ResponseEntity<List<Occupation>> getCustomerByEarningsLessThan(@PathVariable int earnings) {

		return ResponseEntity.ok().body(customerService.getCustomerByEarningsLessThan(earnings));

	}

	@GetMapping("/customers/getByEarningsEqualsTo/{earnings}")
	public ResponseEntity<List<Occupation>> getCustomerByEarningsEqualsTo(@PathVariable int earnings) {

		return ResponseEntity.ok().body(customerService.getCustomerByEarningsEqualsTo(earnings));

	}

	@GetMapping("/customers/getByOccupationByType/{type}")
	public ResponseEntity<List<Object[]>> getOccupationByType(@PathVariable String type) {

		return ResponseEntity.ok().body(customerService.getOccupationByType(type));

	}

}
