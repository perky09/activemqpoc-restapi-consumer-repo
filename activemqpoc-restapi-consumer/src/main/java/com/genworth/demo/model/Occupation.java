package com.genworth.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "occupation")
public class Occupation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*@ManyToOne(targetEntity = JobType.class)*/
	private String type;	
	
	@OneToOne(cascade = CascadeType.ALL,
			orphanRemoval=true)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private Address jobAddress;
	
	private int earnings;

	public Occupation(long id, String type, Address jobAddress, int earnings) {
		super();
		this.id = id;
		this.type = type;
		this.jobAddress = jobAddress;
		this.earnings = earnings;
	}

	public Occupation() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Address getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(Address jobAddress) {
		this.jobAddress = jobAddress;
	}

	public int getEarnings() {
		return earnings;
	}

	public void setEarnings(int earnings) {
		this.earnings = earnings;
	}
	
	

	
}
