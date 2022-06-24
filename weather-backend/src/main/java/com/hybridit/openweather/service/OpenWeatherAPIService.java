package com.hybridit.openweather.service;

import com.hybridit.openweather.dto.api.Forecast;
import com.hybridit.openweather.mapper.CityMapper;
import com.hybridit.openweather.mapper.WeatherMapper;
import com.hybridit.openweather.repository.CityRepository;
import com.hybridit.openweather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class OpenWeatherAPIService {
    @Value("#{'${open-weather-api.cities}'.split(',')}")
    private String[] cities;

    @Value("#{'${open-weather-api.apiKey}'}")
    private String apiKey;

    @Value("#{'${open-weather-api.url}'}")
    private String url;

    private List<Forecast> forecast;
    private final RestTemplate restTemplate;
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;

    public OpenWeatherAPIService(RestTemplateBuilder restTemplateBuilder, CityRepository cityRepository, WeatherRepository weatherRepository) {
        this.forecast = new ArrayList<>();
        this.restTemplate = restTemplateBuilder.build();
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }

    @PostConstruct
    public void init() {

        for (String city : cities) {

            String url = String.format(this.url.concat("?q=%s").concat("&appid=%s").concat("&units=metric"),
                    city, this.apiKey);
            this.forecast.add(this.restTemplate.getForObject(url, Forecast.class));
        }
        saveCities();
        saveWeather();
    }

    private void saveCities() {
        this.forecast.forEach(forecast -> {
            cityRepository.save(CityMapper.mapForecastToCity(forecast));
        });
    }

    private void saveWeather() {
        this.forecast.forEach(forecast -> {
            weatherRepository.saveAll(WeatherMapper.mapForecastToWeather(forecast));
        });
    }

}
