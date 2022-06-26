package com.hybridit.openweather.repository;

import com.hybridit.openweather.projection.CityWeatherData;
import com.hybridit.openweather.model.Weather;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

import java.time.LocalDate;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query( value = " select avg(w.temperature) as avgTemperature, w.city.id as cityId " +
            " from Weather w " +
            " where (w.city.id IN :cityIds OR :cityIds IS NULL) " +
            " and (date between :from and :to) " +
            " group by w.city.id")
    List<CityWeatherData> getAvgTemperature(@Param("cityIds") List<Long> cityIds,
                                            @Param("from") LocalDate from,
                                            @Param("to") LocalDate to,
                                            @Param("sort") Sort sort);
}

