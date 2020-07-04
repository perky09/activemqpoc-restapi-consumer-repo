package com.genworth.demo.xmlmodel;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="firstName")
	private String firstName;
	@XmlElement(name="secondary-occupation")
	private Occupation secondary;
	@XmlElement(name="primary-occupation")
	private Occupation primary;
	@XmlElement(name="cust-id")
	private long id;
	@XmlElement(name="cust-address")
	private Address address;
	private List<Occupation> occupation;
	public Customer(String firstName, Occupation secondary, Occupation primary, long id, Address address) {
		super();
		this.firstName = firstName;
		this.secondary = secondary;
		this.primary = primary;
		this.id = id;
		this.address = address;
	}
	public Customer() {
		super();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Occupation getSecondary() {
		return secondary;
	}
	public void setSecondary(Occupation secondary) {
		this.secondary = secondary;
	}
	public Occupation getPrimary() {
		return primary;
	}
	public void setPrimary(Occupation primary) {
		this.primary = primary;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", secondary=" + secondary + ", primary=" + primary + ", id=" + id
				+ ", address=" + address + "]";
	}
	
	
}
