package com.hackerrank.weather.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;

@Service
public class WeatherService {
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
	
    /**
     * Method to get WeatherDetails based on date
     * @param weatherDate
     * @return List<Weather>
     * @throws ParseException 
     */
    public List<Weather> getWeatherByDate(String weatherDate) throws ParseException{
    	return weatherRepository.findByDate(simpleDateFormat.parse(weatherDate));
    }
    
    /**
     * Method to get WeatherDetails based on city
     * @param city
     * @return List<Weather>
     */
    public List<Weather> getWeatherByCity(String city){
    	return weatherRepository.findByCityIgnoreCase(city);
    	
    } 
    
    /**
     * Method to get WeatherDetails based on sort
     * @param sort
     * @return List<Weather>
     */
    public List<Weather> getWeatherBySort(String sort){
    	
    	if(sort.equalsIgnoreCase("asc")) {
    	  return weatherRepository.findByOrderByDateAsc();
    	}
    	else {
    		return weatherRepository.findByOrderByDateDesc();
    	}
    }
    
    
    /**
     * Method to get WeatherDetails based on Id
     * @param id
     * @return Optional<Weather>
     */
    public Optional<Weather> getWeatherById(Integer id){
    	
    	return weatherRepository.findById(id);
    
    } 
    /**
     * Method to get WeatherDetails based on city
     * @param id
     * @return List<Weather>
     */
    public List<Weather> getAllWeatherDetails(){
    	return weatherRepository.findAll();
    
    } 
    
    /**
     * Method to save or update Weather Details
     * @param Weather
     * @return Weather
     */
    public Weather saveWeatherDetails(Weather weather){
    	return weatherRepository.save(weather);
    }
}
