package com.hybridit.openweather.dto;

public class CityResponse {
    private Long id;
    private String name;
    private String countryCode;

    public CityResponse(Long id, String name, String countryCode) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
