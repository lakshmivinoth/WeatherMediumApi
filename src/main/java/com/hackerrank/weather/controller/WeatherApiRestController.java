package com.hackerrank.weather.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.hackerrank.weather.exception.InvalidRequestException;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;



@RestController
public class WeatherApiRestController {
	
	 @Autowired
	 private WeatherService weatherService;
	 
	 /**
	     * Logger Reference
	     */
	 private static final Logger Log = LoggerFactory.getLogger("WeatherApiRestController.class");
	 
	 /**
	     * Method to get the weatherDetail
	     */
	 @GetMapping(path="/weather", produces = MediaType.APPLICATION_JSON_VALUE) 
	 public  @ResponseBody List<Weather> getWeatherDetails(@RequestParam(required=false) String date,@RequestParam(required=false) String city,@RequestParam(required=false) String sort) throws ParseException{
	    	Log.info("Inside Weather Controller to Get the Weather Detail");
	    	List<Weather> weatherDetail = null;
	    	
	    	if(date!=null) {
	    	 weatherDetail = weatherService.getWeatherByDate(date);
	    	}
	    	else if(city!=null) {
	    	 weatherDetail = weatherService.getWeatherByCity(city);
	    	}
	    	else if(sort!=null) {
	    		if(sort.equalsIgnoreCase("date")) {
	    			weatherDetail = weatherService.getWeatherBySort("asc");
	   	    	}
	   	    	else {
	   	    		weatherDetail = weatherService.getWeatherBySort("desc");
	   	    	}	
	    	}
	    	else {
	    		weatherDetail=weatherService.getAllWeatherDetails();	
	    	}
	    	
	    	if(weatherDetail.isEmpty()) {
	    		Log.error("Weather Details not Found");
	    		return new ArrayList<Weather>();
	    	}
	    	
	    	return weatherDetail;
	    }
	 
		 
	 /**
	     * Method to get the  WeatherDetail based on id
	     */
	 @GetMapping(path="/weather/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
	 public ResponseEntity<Weather> getWeatherDetailsById(@PathVariable Integer id){
	    	Log.info("Inside Weather Controller to Get the Weather Detail by id");
	    	Optional<Weather> weatherById = weatherService.getWeatherById(id);
	    	if(weatherById.isPresent()) {
	    		Log.info("Record with Id Found");
	    		return new ResponseEntity<Weather>(weatherById.get(),HttpStatus.OK);
	    	}
	    	else {
	    		Log.error("Weather Details not Found");
	    		return new ResponseEntity<Weather>(HttpStatus.NOT_FOUND);
	    	}
	    }
	 
	 /**
	     * Method to save the weather details
	     */
	    @PostMapping(path="/weather", produces = MediaType.APPLICATION_JSON_VALUE,
	    		consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Weather>  saveWeatherDetails(@RequestBody Weather weather){
	    	Log.info("Inside Weather Controller to save the Weather Detail");
	    	
	    	Weather weatherDetail = weatherService.saveWeatherDetails(weather);
	    	if(weatherDetail == null) {
	    		Log.error("The save request is not valid");
	    		throw new InvalidRequestException("Request is Invalid, Please enter all the required details");
	    	}
	    	else {	    		
	    		return new ResponseEntity<Weather>(weather,HttpStatus.CREATED);
	    	}
	    	
	    }
}
