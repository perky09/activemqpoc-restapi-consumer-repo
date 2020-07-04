package com.genworth.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomedException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public CustomedException(String message) {
		super(message);
	}
	
	public CustomedException(String message , Throwable throwable) {
		super(message , throwable);
	}

}



