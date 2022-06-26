package com.hybridit.openweather.controller;

import com.hybridit.openweather.projection.CityWeatherData;
import com.hybridit.openweather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/avg-temperature")
    public ResponseEntity<List<CityWeatherData>> getAvgTemp(@RequestParam(required = false) List<Long> cityIds,
                                                      @RequestParam String from,
                                                      @RequestParam String to,
                                                      @RequestParam(required = false, defaultValue = "asc") String sortType) {

        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return ResponseEntity.ok(weatherService.getAvgTemperature(cityIds, fromDate, toDate, sortType));
    }
}
