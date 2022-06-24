package com.hybridit.openweather.mapper;

import com.hybridit.openweather.dto.CityResponse;
import com.hybridit.openweather.dto.api.Forecast;
import com.hybridit.openweather.model.City;

public class CityMapper {

    public static City mapForecastToCity(Forecast forecast) {
        return new City(forecast.getCity().getId(), forecast.getCity().getName(), forecast.getCity().getCountry());
    }

    public static CityResponse mapToCityResponse(City city) {
        return new CityResponse(city.getId(), city.getName(), city.getCountryCode());
    }
}
