package com.hybridit.openweather.repository;

import com.hybridit.openweather.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
