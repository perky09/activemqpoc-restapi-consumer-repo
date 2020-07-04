package com.genworth.demo.controller.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.genworth.demo.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTests {
	
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	int randomServerPort;
	
	
	
	@Test
	public void testGetCustomer() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/customers";
		  URI uri = new URI(baseUrl);		
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		  ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.GET, entity,
		  String.class); 
		  Assert.assertEquals(200, result.getStatusCodeValue()); 
		
	}
	
	@Test
	public void testGetCustomerByName() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/customers/getByName/Prakash";
		  URI uri = new URI(baseUrl);	
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		  ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.GET, entity,
				  String.class); 
		  
		  Assert.assertEquals(200, result.getStatusCodeValue()); 
		
	}
	
	@Test
	public void testGetCustomerByID() throws URISyntaxException {
		  final String baseUrl = "http://localhost:"+randomServerPort+"/customers/6";
		  URI uri = new URI(baseUrl);		  
		  ResponseEntity<Customer> result = this.restTemplate.getForEntity(uri, Customer.class);		  
		  Assert.assertEquals(200, result.getStatusCodeValue()); 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Test
	public void testCreateCustomer() throws URISyntaxException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/customers/";
		URI uri = new URI(baseUrl);
		Customer customer = new Customer();
		Address address = new Address();		
		customer.setName("Prakash");
		address.setCity("kolkata");
		address.setLine1("Park Street");
		address.setLine2("Gramhams Land");
		address.setState("WB");
		address.setZip("12345");
		customer.setAddress(address);
		Occupation occupation=new Occupation();
		occupation.setEarnings(5000);
		occupation.setJobAddress(address);
		occupation.setType("primary");
		List<Occupation> lstOccupation = new ArrayList<>();
		lstOccupation.add(occupation);
		customer.setOccupation(lstOccupation);	
		
		ResponseEntity<Customer> result = restTemplate.postForEntity(uri, customer,
				Customer.class);
		
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		
	}*/
	
	

	/*@Test
	public void contextLoads() {
	}*/

}
