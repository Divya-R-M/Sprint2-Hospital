package com.cg.healthassist.hospitalmodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * The Exception class which handles exceptions when the given Patient Id is not available
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PatientIDException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientIDException() {
		super();
	}

	public PatientIDException(String errMsg) {
		super(errMsg);
	}
}
