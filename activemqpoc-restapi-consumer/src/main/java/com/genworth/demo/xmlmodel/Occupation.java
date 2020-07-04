package com.genworth.demo.xmlmodel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="occupation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Occupation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="salary")
	private String salary;
	@XmlElement(name="jobLocation")
	private Address jobLocation;
	private String type;
	
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public Address getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(Address jobLocation) {
		this.jobLocation = jobLocation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Occupation [salary=" + salary + ", jobLocation=" + jobLocation + "]";
	}

}
