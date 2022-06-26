package com.hybridit.openweather.controller;

import com.hybridit.openweather.dto.CityResponse;
import com.hybridit.openweather.mapper.CityMapper;
import com.hybridit.openweather.service.CityService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Operation(summary = "Get all cities in the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All cities in the database")})
    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities() {
        List<CityResponse> cities = cityService.getAllCities().stream()
                .map(city -> CityMapper.mapToCityResponse(city))
                .collect(Collectors.toList());
        return ResponseEntity.ok(cities);
    }
}
