package com.hybridit.openweather.mapper;

import com.hybridit.openweather.dto.api.Forecast;
import com.hybridit.openweather.dto.api.WeatherInfo;
import com.hybridit.openweather.model.Weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WeatherMapper {

    public static List<Weather> mapForecastToWeather(Forecast forecast){
        List<Weather> weatherList = new ArrayList<>();
        for (WeatherInfo weatherInfo : forecast.getList()) {
            weatherList.add(new Weather(
                    LocalDate.parse(weatherInfo.getDt_txt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    weatherInfo.getMain().getTemp(),
                    CityMapper.mapForecastToCity(forecast)
            ));
        }
        return weatherList;
    }
}
