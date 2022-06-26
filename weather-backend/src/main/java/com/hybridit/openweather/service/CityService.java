package com.hybridit.openweather.service;

import com.hybridit.openweather.model.City;
import com.hybridit.openweather.repository.CityRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public void save(City city){
        cityRepository.save(city);
    }

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
