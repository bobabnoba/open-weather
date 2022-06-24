package com.hybridit.openweather.model;

import javax.persistence.*;

@Entity
public class City {
    @Id
    private Long id;
    private String name;
    private String countryCode;

    public City(){}

    public City(Long id, String name, String countryCode) {
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
