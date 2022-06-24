package com.hybridit.openweather.controller;

import com.hybridit.openweather.dto.CityResponse;
import com.hybridit.openweather.mapper.CityMapper;
import com.hybridit.openweather.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities() {
        List<CityResponse> cities = cityService.getAllCities().stream()
                .map(city -> CityMapper.mapToCityResponse(city))
                .collect(Collectors.toList());
        return ResponseEntity.ok(cities);
    }
}
