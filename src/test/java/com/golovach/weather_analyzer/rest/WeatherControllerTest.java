package com.golovach.weather_analyzer.rest;

import com.golovach.weather_analyzer.entity.CurrentWeather;
import com.golovach.weather_analyzer.entity.Weather;
import com.golovach.weather_analyzer.entity.WeatherCondition;
import com.golovach.weather_analyzer.entity.WeatherLocation;
import com.golovach.weather_analyzer.repository.WeatherRepository;
import com.golovach.weather_analyzer.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/delete-weather-before.sql"})
class WeatherControllerTest {

    /** @noinspection SpringJavaInjectionPointsAutowiringInspection*/
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WeatherService weatherService;
    @Autowired
    WeatherRepository weatherRepository;

    @Test
    public void handleGetCurrentWeather_ReturnsValidResponseEntity() throws Exception {
        //given
        RequestBuilder requestBuilder = get("/api/weather/");

        //when
        mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("{\"tempC\":10,\"windMph\":11.9,\"pressureMb\":1017,\"humidity\":100,\"weatherCondition\":\"Partly cloudy\",\"location\":\"Minsk\"}")
                );

    }

    @Test
    public void handleAverageWeather_PayloadIsValid_ReturnsValidResponseEntity() throws Exception {
        //given
        RequestBuilder requestBuilder = post("/api/weather/average")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"from\":\"17-12-2023\",\n" +
                        "    \"to\":\"19-12-2023\"\n" +
                        "}");

        //when
        mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("{\"average_temp_c\":7.0,\"average_wind_mph\":11.9,\"average_pressure_mb\":1017.0,\"average_humidity\":100.0}")
                );
    }

    @Test
    public void handleAverageWeather_PayloadIsInvalid_ReturnsValidResponseEntity() throws Exception {
        //given
        RequestBuilder requestBuilder = post("/api/weather/average")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"from\":\"15-12-2023\",\n" +
                        "    \"to\":\"19-12-2023\"\n" +
                        "}");

        //when
        mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("{\"message\":\"Date interval not found, available interval: 2023-12-17 2023-12-19 \"}")
                );
    }

    @BeforeEach
    private void saveWeathers() {
        weatherRepository.saveAll(List.of(
                getWeather(1, LocalDateTime.parse("2023-12-17T10:15:30"), 4),
                getWeather(2, LocalDateTime.parse("2023-12-18T10:15:30"), 7),
                getWeather(3, LocalDateTime.parse("2023-12-19T10:15:30"), 10)
        ));
    }

    private Weather getWeather(long id, LocalDateTime localDateTime, int tempC) {
        Weather weather = new Weather();
        CurrentWeather currentWeather = new CurrentWeather();
        WeatherLocation weatherLocation = new WeatherLocation();
        WeatherCondition weatherCondition = new WeatherCondition();

        weatherCondition.setId(id);
        weatherCondition.setCurrentWeather(currentWeather);
        weatherCondition.setCode(1003);
        weatherCondition.setIcon("//cdn.weatherapi.com/weather/64x64/day/116.png");
        weatherCondition.setText("Partly cloudy");

        weatherLocation.setWeather(weather);
        weatherLocation.setId(id);
        weatherLocation.setCountry("Belarus");
        weatherLocation.setLatitude(53.9);
        weatherLocation.setLocaltimeEpoch(1702811747);
        weatherLocation.setName("Minsk");
        weatherLocation.setLocalDateTime(localDateTime);
        weatherLocation.setRegion("Minsk");
        weatherLocation.setTimeZoneId("Europe/Minsk");
        weatherLocation.setLongitude(27.55);

        currentWeather.setWeather(weather);
        currentWeather.setId(id);
        currentWeather.setCloud(75);
        currentWeather.setFeelsLikeC(-0.2);
        currentWeather.setFeelsLikeF(31.6);
        currentWeather.setGustKph(35.1);
        currentWeather.setGustMph(21.8);
        currentWeather.setHumidity(100);
        currentWeather.setIsDay(1);
        currentWeather.setLastUpdated(localDateTime);
        currentWeather.setLastUpdatedEpoch(1702810800);
        currentWeather.setPrecipIn(0);
        currentWeather.setPrecipMm(0);
        currentWeather.setPressureIn(30.03);
        currentWeather.setPressureMb(1017);
        currentWeather.setTempC(tempC);
        currentWeather.setTempF(39);
        currentWeather.setUv(1);
        currentWeather.setVisKm(8);
        currentWeather.setVisMiles(4);
        currentWeather.setWindKph(19.1);
        currentWeather.setWindMph(11.9);
        currentWeather.setWeatherCondition(weatherCondition);

        weather.setCurrentWeather(currentWeather);
        weather.setWeatherLocation(weatherLocation);
        weather.setId(id);

        return weather;
    }
}