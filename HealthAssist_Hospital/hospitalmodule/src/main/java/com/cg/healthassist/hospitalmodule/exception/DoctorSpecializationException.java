package com.cg.healthassist.hospitalmodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * The Exception class which handles exceptions when the given Doctor Specialization is not available
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoctorSpecializationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoctorSpecializationException() {
		super();
	}

	public DoctorSpecializationException(String errMsg) {
		super(errMsg);
	}
}
