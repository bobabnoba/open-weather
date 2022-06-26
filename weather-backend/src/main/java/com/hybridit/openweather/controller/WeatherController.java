package com.hybridit.openweather.controller;

import com.hybridit.openweather.projection.CityWeatherData;
import com.hybridit.openweather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Get average temperature for cities in a given date range",
            description = "Get average temperature for cities with given ids or all cities from the database " +
                    "if no city ids are given. The date range is given in the format YYYY-MM-DD." +
                    "Provides possibility of sorting results by average temperature.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of city ids with corresponding average temperatures for given date range")})
    @GetMapping
    public ResponseEntity<List<CityWeatherData>> getAvgTemp(
        @Parameter(description = "List of ids of cities separated by comma") @RequestParam(required = false) List<Long> cityIds,
        @Parameter(description = "Starting point of date range (YYYY-MM-DD)") @RequestParam String from,
        @Parameter(description = "Ending point of date range (YYYY-MM-DD)") @RequestParam String to,
        @Parameter(description = "Sort direction, ASC or DESC") @RequestParam(required = false, defaultValue = "asc") String order) {

        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return ResponseEntity.ok(weatherService.getAvgTemperature(cityIds, fromDate, toDate, order));
    }
}
