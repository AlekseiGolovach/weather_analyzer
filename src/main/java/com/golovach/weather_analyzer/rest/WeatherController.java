package com.golovach.weather_analyzer.rest;

import com.golovach.weather_analyzer.dto.AverageWeatherDTO;
import com.golovach.weather_analyzer.dto.CurrentWeatherDTO;
import com.golovach.weather_analyzer.entity.Weather;
import com.golovach.weather_analyzer.facade.AverageWeatherFacade;
import com.golovach.weather_analyzer.facade.WeatherFacade;
import com.golovach.weather_analyzer.payload.request.FromToDateWeatherRequest;
import com.golovach.weather_analyzer.service.WeatherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final WeatherFacade weatherFacade;
    private final AverageWeatherFacade averageWeatherFacade;

    @Autowired
    public WeatherController(WeatherService weatherService, WeatherFacade weatherFacade, AverageWeatherFacade averageWeatherFacade) {
        this.weatherService = weatherService;
        this.weatherFacade = weatherFacade;
        this.averageWeatherFacade = averageWeatherFacade;
    }

    @GetMapping("/")
    public ResponseEntity<CurrentWeatherDTO> getCurrentWeather() {
        Weather weather = weatherService.getCurrentWeather();
        CurrentWeatherDTO currentWeatherDTO = weatherFacade.weatherToWeatherDTO(weather);
        return new ResponseEntity<>(currentWeatherDTO, HttpStatus.OK);
    }

    @PostMapping("/average")
    public ResponseEntity<?> averageWeather(@Valid @RequestBody FromToDateWeatherRequest fromToDateWeatherRequest) {
        List<Weather> weatherList = weatherService.getAllWeatherFromToDate(fromToDateWeatherRequest);
        AverageWeatherDTO averageWeatherDTO = averageWeatherFacade.listWeatherToAverageWeatherDTO(weatherList);
        return new ResponseEntity<>(averageWeatherDTO, HttpStatus.OK);
    }
}
