package com.hackerrank.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class WeatherDetailsNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public WeatherDetailsNotFoundException(String exception) {
        super(exception);
    }

}
