package com.genworth.demo.jsonmodel;

import java.util.List;

public class CustomerDTO {
	private long id;
	private String name;
	private Address address;
	private List<Occupation> occupation;
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
	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", address=" + address + ", occupation=" + occupation + "]";
	}
	

}
