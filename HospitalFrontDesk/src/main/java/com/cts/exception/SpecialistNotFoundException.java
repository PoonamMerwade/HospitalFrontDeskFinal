package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpecialistNotFoundException extends RuntimeException {
	public SpecialistNotFoundException(String exception) {
        super(exception);
    }


}
