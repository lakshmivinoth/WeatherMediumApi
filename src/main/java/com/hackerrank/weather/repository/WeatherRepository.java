package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	
	List<Weather> findByDate(Date date);
	List<Weather> findByCityIgnoreCase(String city);
	List<Weather> findByOrderByDateAsc();
	List<Weather> findByOrderByDateDesc();
	
}
