package com.hybridit.openweather.service;

import com.hybridit.openweather.model.Weather;
import com.hybridit.openweather.projection.CityWeatherData;
import com.hybridit.openweather.repository.WeatherRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDate;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void saveWeatherInfo(List<Weather> weatherInfo){
        weatherRepository.saveAll(weatherInfo);
    }

    public List<CityWeatherData> getAvgTemperature(List<Long> cityIds, LocalDate from, LocalDate to, String sortType) {
        Sort sort = sortType.equalsIgnoreCase("desc") ? Sort.by("avgTemperature").descending() : Sort.by("avgTemperature").ascending();
        return weatherRepository.getAvgTemperature(cityIds, from, to, sort);
    }
}
