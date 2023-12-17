package com.golovach.weather_analyzer.exception;

public class WeatherDataNotFoundException extends RuntimeException {
    public WeatherDataNotFoundException(String s) {
        super(s);
    }

}
