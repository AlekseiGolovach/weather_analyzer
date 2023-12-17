package com.golovach.weather_analyzer.service;

import com.golovach.weather_analyzer.entity.Weather;
import com.golovach.weather_analyzer.rapidapi.RapidApi;
import com.golovach.weather_analyzer.rapidapi.ParserJson;
import com.golovach.weather_analyzer.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RapidApiService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RapidApiService.class);

    private final WeatherRepository weatherRepository;
    private final ParserJson parserJson;
    private final RapidApi rapidApi;

    public RapidApiService(WeatherRepository weatherRepository, ParserJson parserJson, RapidApi rapidApi) {
        this.weatherRepository = weatherRepository;
        this.parserJson = parserJson;
        this.rapidApi = rapidApi;
    }

    @Scheduled(cron = "${rapidapi.service.interval.cron}")
    private void saveWeatherWithTimeout() {
        String json = rapidApi.pollWeatherJson();
        Weather weather = parserJson.parseJsonToObject(json);
        if (weather != null) {
            LOGGER.info("Saving weather {}", weather.getCurrentWeather().getLastUpdatedEpoch());
            weatherRepository.save(weather);
        }
    }
}
