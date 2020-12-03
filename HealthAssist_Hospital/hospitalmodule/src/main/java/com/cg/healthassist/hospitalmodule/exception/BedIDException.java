package com.cg.healthassist.hospitalmodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BedIDException extends RuntimeException {

	public BedIDException() {
		super();
	}

	public BedIDException(String errMsg) {
		super(errMsg);
	}
}
