package com.golovach.weather_analyzer.facade;

import com.golovach.weather_analyzer.dto.CurrentWeatherDTO;
import com.golovach.weather_analyzer.entity.Weather;
import org.springframework.stereotype.Component;

@Component
public class WeatherFacade {

    public CurrentWeatherDTO weatherToWeatherDTO(Weather weather) {
        CurrentWeatherDTO currentWeatherDTO = new CurrentWeatherDTO();
        currentWeatherDTO.setTempC(weather.getCurrentWeather().getTempC());
        currentWeatherDTO.setWindMph(weather.getCurrentWeather().getWindMph());
        currentWeatherDTO.setPressureMb(weather.getCurrentWeather().getPressureMb());
        currentWeatherDTO.setHumidity(weather.getCurrentWeather().getHumidity());
        currentWeatherDTO.setWeatherCondition(weather.getCurrentWeather().getWeatherCondition().getText());
        currentWeatherDTO.setLocation(weather.getWeatherLocation().getName());
        return currentWeatherDTO;
    }
}
