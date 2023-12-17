package com.golovach.weather_analyzer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class WeatherCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @OneToOne(mappedBy = "weatherCondition")
    @JsonIgnore
    private CurrentWeather currentWeather;

    private String text;
    private String icon;
    private long code;

    @Override
    public String toString() {
        return "WeatherCondition{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", code=" + code +
                '}';
    }
}
