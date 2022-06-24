package com.hybridit.openweather.repository;

import com.hybridit.openweather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}

