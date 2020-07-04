package com.genworth.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL,
			orphanRemoval=true)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	//name of the column that will store foreign key - and it will store primary key of address entity 
	private Address address;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval=true
				)
	@JoinColumn(name="customer_id", referencedColumnName = "id")
	private List<Occupation> occupation = new ArrayList<>();

	public Customer(long id, String name, Address address, List<Occupation> occupation) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.occupation = occupation;
	}

	public Customer() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Occupation> getOccupation() {
		return occupation;
	}

	public void setOccupation(List<Occupation> occupation) {
		this.occupation = occupation;
	}

	
	
	
	
}
