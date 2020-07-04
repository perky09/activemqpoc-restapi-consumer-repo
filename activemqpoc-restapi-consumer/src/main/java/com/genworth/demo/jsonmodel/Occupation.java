package com.genworth.demo.jsonmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Occupation {
	
	private String type;
	private Address jobAddress;
	@JsonProperty("earnings")
	private int salary;
	
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	

}
