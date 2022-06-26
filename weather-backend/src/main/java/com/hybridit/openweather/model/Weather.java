package com.hybridit.openweather.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double temperature;
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    public Weather() {
    }

    public Weather(LocalDate dateAndTime, Double temperature, City city) {
        this.date = dateAndTime;
        this.temperature = temperature;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public City getCity() {
        return city;
    }
}
