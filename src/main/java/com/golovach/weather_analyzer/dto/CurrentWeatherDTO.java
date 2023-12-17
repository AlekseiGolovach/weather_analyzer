package com.golovach.weather_analyzer.dto;

import lombok.Data;

@Data
public class CurrentWeatherDTO {
    private int tempC;
    private double windMph;
    private int pressureMb;
    private int humidity;
    private String weatherCondition;
    private String location;
}
