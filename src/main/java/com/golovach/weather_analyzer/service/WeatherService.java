package com.golovach.weather_analyzer.service;

import com.golovach.weather_analyzer.entity.Weather;
import com.golovach.weather_analyzer.exception.WeatherDataNotFoundException;
import com.golovach.weather_analyzer.payload.request.FromToDateWeatherRequest;
import com.golovach.weather_analyzer.repository.WeatherRepository;
import com.golovach.weather_analyzer.validation.DateIntervalValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WeatherService {
    public static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherRepository weatherRepository;
    private final DateIntervalValidator dateIntervalValidator;

    private Weather firstWeatherInRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository, DateIntervalValidator dateIntervalValidator) {
        this.weatherRepository = weatherRepository;
        this.dateIntervalValidator = dateIntervalValidator;
    }

    public Weather getCurrentWeather() {
        return weatherRepository.findCurrentWeather().orElseThrow(()-> {
            String s = "Cannot find current data weather";
            LOGGER.error(s);
            return new WeatherDataNotFoundException(s);
        });
    }

    public List<Weather> getAllWeatherFromToDate(FromToDateWeatherRequest fromToDateWeatherRequest) {
        if (firstWeatherInRepository == null) {
            firstWeatherInRepository = weatherRepository.findFirstWeather().orElseThrow(()->{
                String s = "Cannot find first data weather in repository";
                LOGGER.error(s);
                return new WeatherDataNotFoundException(s);
            });
        }
        LocalDate commonFirst = firstWeatherInRepository.getCurrentWeather().getLastUpdated().toLocalDate();
        LocalDate commonSecond = getCurrentWeather().getCurrentWeather().getLastUpdated().toLocalDate();

        LocalDate givenFirst = stringToLDT(fromToDateWeatherRequest.getFromDate());
        LocalDate givenSecond = stringToLDT(fromToDateWeatherRequest.getToDate());

        dateIntervalValidator.commonIntervalIncludesGivenInterval(commonFirst, givenFirst, givenSecond, commonSecond);

        return weatherRepository.findAllWeatherWhereLastUpdateBetweenFromTo(givenFirst, givenSecond.plusDays(1));
    }

    private LocalDate stringToLDT(String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(s, dateTimeFormatter);
    }
}
