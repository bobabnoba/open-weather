package com.hybridit.openweather.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureDetails {
    private double temp;

    public TemperatureDetails() {
    }

    public double getTemp() {
        return temp;
    }

}
