package com.genworth.demo.constants;

public final class RestConstant {
	
	private RestConstant() {
		
	}
	
	public static final String CREATE_CUSTOMER = "http://localhost:8081/customers";
	public static final String GETALL_CUSTOMER = "http://localhost:8081/customers";
	public static final String GET_CUSTOMER_BY_NAME = "http://localhost:8081/customers/getByName/{name}";
	
	public static final int FIRST = 0;
	public static final int SECOND = 1;
	public static final String PRIMARYTYPE = "primary";
	public static final String SECONDARYTYPE ="secondary";
	public static final String QUEUENAME = "demo";
	
	
	
	
	

}
