package com.golovach.weather_analyzer.service;

import com.golovach.weather_analyzer.entity.CurrentWeather;
import com.golovach.weather_analyzer.entity.Weather;
import com.golovach.weather_analyzer.payload.request.FromToDateWeatherRequest;
import com.golovach.weather_analyzer.repository.WeatherRepository;
import com.golovach.weather_analyzer.validation.DateIntervalValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherRepository mockWeatherRepository;

    @Mock
    private DateIntervalValidator mockDateIntervalValidator;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void handleGetCurrentWeather_callWeatherRepositoryFindCurrentWeather() {
        //given
        when(mockWeatherRepository.findCurrentWeather()).thenReturn(Optional.of(new Weather()));
        //when
        weatherService.getCurrentWeather();
        //then
        verify(mockWeatherRepository).findCurrentWeather();

    }

    @Test
    void handleGetAllWeatherFromToDate() {
        Weather mockWeather = mock(Weather.class);
        CurrentWeather mockCurrentWeather = mock(CurrentWeather.class);
        FromToDateWeatherRequest fromToDateWeatherRequest = new FromToDateWeatherRequest("17-12-2023", "19-12-2023");
        when(mockWeather.getCurrentWeather()).thenReturn(mockCurrentWeather);
        when(mockCurrentWeather.getLastUpdated()).thenReturn(LocalDateTime.parse("2023-12-01T10:15:30"));
        when(mockWeatherRepository.findCurrentWeather()).thenReturn(Optional.of(mockWeather));
        when(mockWeatherRepository.findFirstWeather()).thenReturn(Optional.of(mockWeather));

        //when
        weatherService.getAllWeatherFromToDate(fromToDateWeatherRequest);
        //then
        verify(mockWeatherRepository).findCurrentWeather();
        verify(mockDateIntervalValidator).commonIntervalIncludesGivenInterval(any(LocalDate.class), any(LocalDate.class), any(LocalDate.class), any(LocalDate.class));
        verify(mockWeatherRepository).findAllWeatherWhereLastUpdateBetweenFromTo(any(), any());
    }
}