package com.golovach.weather_analyzer.rest;

import com.golovach.weather_analyzer.dto.AverageWeatherDTO;
import com.golovach.weather_analyzer.dto.CurrentWeatherDTO;
import com.golovach.weather_analyzer.entity.CurrentWeather;
import com.golovach.weather_analyzer.entity.Weather;
import com.golovach.weather_analyzer.entity.WeatherCondition;
import com.golovach.weather_analyzer.entity.WeatherLocation;
import com.golovach.weather_analyzer.facade.AverageWeatherFacade;
import com.golovach.weather_analyzer.facade.WeatherFacade;
import com.golovach.weather_analyzer.payload.request.FromToDateWeatherRequest;
import com.golovach.weather_analyzer.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;
    @Mock
    private WeatherFacade weatherFacade;
    @Mock
    private AverageWeatherFacade averageWeatherFacade;

    @InjectMocks
    private WeatherController weatherController;


    @Test
    void handleGetCurrentWeatherDTO_ReturnsValidResponseEntity() {
        //given
        Weather weather = getWeather();
        CurrentWeatherDTO currentWeatherDTO = getCurrentWeatherDTO(weather);
        when(weatherFacade.weatherToWeatherDTO(weatherService.getCurrentWeather())).thenReturn(currentWeatherDTO);

        //when
        ResponseEntity<CurrentWeatherDTO> responseEntity = weatherController.getCurrentWeather();

        //then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(currentWeatherDTO, responseEntity.getBody());
    }

    @Test
    void handleAverageWeather_PayloadIsValid_ReturnsValidResponseEntity() {
        //given
        FromToDateWeatherRequest fromToDateWeatherRequest = new FromToDateWeatherRequest("17-12-2023", "17-12-2023");
        List<Weather> weatherList = List.of(getWeather(), getWeather());
        AverageWeatherDTO averageWeatherDTO = getAverageWeatherDTO();
        when(weatherService.getAllWeatherFromToDate(fromToDateWeatherRequest)).thenReturn(weatherList);
        when(averageWeatherFacade.listWeatherToAverageWeatherDTO(weatherList)).thenReturn(averageWeatherDTO);
        //when
        ResponseEntity<?> responseEntity = weatherController.averageWeather(fromToDateWeatherRequest);

        //then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        if (responseEntity.getBody() instanceof AverageWeatherDTO) {
            AverageWeatherDTO returnedAverageWeatherDTO = (AverageWeatherDTO) responseEntity.getBody();
            assertEquals(returnedAverageWeatherDTO.getAverageHumidity(), averageWeatherDTO.getAverageHumidity());
            assertEquals(returnedAverageWeatherDTO.getAverageTempC(), averageWeatherDTO.getAverageTempC());
            assertEquals(returnedAverageWeatherDTO.getAverageWindMph(), averageWeatherDTO.getAverageWindMph());
            assertEquals(returnedAverageWeatherDTO.getAveragePressureMb(), averageWeatherDTO.getAveragePressureMb());

        }

    }

    private CurrentWeatherDTO getCurrentWeatherDTO(Weather weather) {
        CurrentWeatherDTO currentWeatherDTO = new CurrentWeatherDTO();
        currentWeatherDTO.setWeatherCondition(weather.getCurrentWeather().getWeatherCondition().getText());
        currentWeatherDTO.setHumidity(weather.getCurrentWeather().getHumidity());
        currentWeatherDTO.setWindMph(weather.getCurrentWeather().getWindMph());
        currentWeatherDTO.setPressureMb(weather.getCurrentWeather().getPressureMb());
        currentWeatherDTO.setTempC(weather.getCurrentWeather().getTempC());
        currentWeatherDTO.setLocation(weather.getWeatherLocation().getName());
        return currentWeatherDTO;
    }
    private AverageWeatherDTO getAverageWeatherDTO() {
        AverageWeatherDTO averageWeatherDTO = new AverageWeatherDTO();
        averageWeatherDTO.setAverageHumidity(100);
        averageWeatherDTO.setAverageTempC(4);
        averageWeatherDTO.setAverageWindMph(11.9);
        averageWeatherDTO.setAveragePressureMb(1017);
        return averageWeatherDTO;
    }

    private Weather getWeather() {
        Weather weather = new Weather();
        CurrentWeather currentWeather = new CurrentWeather();
        WeatherLocation weatherLocation = new WeatherLocation();
        WeatherCondition weatherCondition = new WeatherCondition();

        weatherCondition.setId(1L);
        weatherCondition.setCurrentWeather(currentWeather);
        weatherCondition.setCode(1003);
        weatherCondition.setIcon("//cdn.weatherapi.com/weather/64x64/day/116.png");
        weatherCondition.setText("Partly cloudy");

        weatherLocation.setWeather(weather);
        weatherLocation.setId(1L);
        weatherLocation.setCountry("Belarus");
        weatherLocation.setLatitude(53.9);
        weatherLocation.setLocaltimeEpoch(1702811747);
        weatherLocation.setName("Minsk");
        weatherLocation.setLocalDateTime(LocalDateTime.parse("2023-12-17T10:15:30"));
        weatherLocation.setRegion("Minsk");
        weatherLocation.setTimeZoneId("Europe/Minsk");
        weatherLocation.setLongitude(27.55);

        currentWeather.setWeather(weather);
        currentWeather.setId(1L);
        currentWeather.setCloud(75);
        currentWeather.setFeelsLikeC(-0.2);
        currentWeather.setFeelsLikeF(31.6);
        currentWeather.setGustKph(35.1);
        currentWeather.setGustMph(21.8);
        currentWeather.setHumidity(100);
        currentWeather.setIsDay(1);
        currentWeather.setLastUpdated(LocalDateTime.parse("2023-12-17T10:15:30"));
        currentWeather.setLastUpdatedEpoch(1702810800);
        currentWeather.setPrecipIn(0);
        currentWeather.setPrecipMm(0);
        currentWeather.setPressureIn(30.03);
        currentWeather.setPressureMb(1017);
        currentWeather.setTempC(4);
        currentWeather.setTempF(39);
        currentWeather.setUv(1);
        currentWeather.setVisKm(8);
        currentWeather.setVisMiles(4);
        currentWeather.setWindKph(19.1);
        currentWeather.setWindMph(11.9);
        currentWeather.setWeatherCondition(weatherCondition);

        weather.setCurrentWeather(currentWeather);
        weather.setWeatherLocation(weatherLocation);
        weather.setId(1L);

        return weather;
    }

    private Optional<Weather> getOptionalWeather() {
        Optional<Weather> optional = Optional.of(getWeather());
        return optional;
    }

}