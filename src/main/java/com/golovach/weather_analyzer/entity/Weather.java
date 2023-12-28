package com.golovach.weather_analyzer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonSetter("location")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_location_id", referencedColumnName = "id")
    private WeatherLocation weatherLocation;

    @JsonSetter("current")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_weather_id", referencedColumnName = "id")
    private CurrentWeather currentWeather;



}
