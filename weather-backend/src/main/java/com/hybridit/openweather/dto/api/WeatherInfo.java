package com.hybridit.openweather.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {
    private String dt_txt;
    private TemperatureDetails main;

    public WeatherInfo() {}

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public TemperatureDetails getMain() {
        return main;
    }

    public void setMain(TemperatureDetails main) {
        this.main = main;
    }

}
