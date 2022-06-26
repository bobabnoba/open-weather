package com.hybridit.openweather.config;

import com.hybridit.openweather.dto.api.Forecast;
import com.hybridit.openweather.mapper.CityMapper;
import com.hybridit.openweather.mapper.WeatherMapper;
import com.hybridit.openweather.service.CityService;
import com.hybridit.openweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitConfig {

    @Value("#{'${open-weather-api.cities}'.split(',')}")
    private String[] cities;

    @Value("#{'${open-weather-api.apiKey}'}")
    private String apiKey;

    @Value("#{'${open-weather-api.url}'}")
    private String url;

    private List<Forecast> forecast;
    private final CityService cityService;
    private final WeatherService weatherService;

    public InitConfig(CityService cityService, WeatherService weatherService) {
        this.cityService = cityService;
        this.weatherService = weatherService;
        this.forecast = new ArrayList<>();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            for (String city : cities) {

                String url = String.format(this.url.concat("?q=%s").concat("&appid=%s").concat("&units=metric"),
                        city, this.apiKey);

                this.forecast.add(restTemplate.getForObject(url, Forecast.class));
            }
            saveCity();
            saveWeather();
        };
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    private void saveCity() {
        this.forecast.forEach(forecast -> {
            cityService.save(CityMapper.mapForecastToCity(forecast));
        });
    }

    private void saveWeather() {
        this.forecast.forEach(forecast -> {
            weatherService.saveWeatherInfo(WeatherMapper.mapForecastToWeather(forecast));
        });
    }
}
