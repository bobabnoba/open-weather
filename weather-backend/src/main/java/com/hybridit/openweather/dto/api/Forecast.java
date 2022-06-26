package com.hybridit.openweather.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast implements Serializable {
    private List<WeatherInfo> list;
    private CityDetails city;

    public Forecast(){}

    public List<WeatherInfo> getList() {
        return list;
    }

    public CityDetails getCity() {
        return city;
    }

}
