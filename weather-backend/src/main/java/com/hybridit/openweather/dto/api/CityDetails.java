package com.hybridit.openweather.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDetails  implements Serializable {
    private Long id;
    private String name;
    private String country;

    public CityDetails() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
