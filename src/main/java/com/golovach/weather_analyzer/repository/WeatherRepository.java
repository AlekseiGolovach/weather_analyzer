package com.golovach.weather_analyzer.repository;

import com.golovach.weather_analyzer.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query(value = "select *" +
            "from weather w where w.current_weather_id = " +
            "(select cw.id from current_weather cw order by last_updated desc limit 1)", nativeQuery = true)
    Optional<Weather> findCurrentWeather();

    @Query(value = "select * " +
            "from weather " +
            "where current_weather_id in" +
            "      (select cw.id from current_weather cw where cw.last_updated between ?1 and ?2)", nativeQuery = true)
    List<Weather> findAllWeatherWhereLastUpdateBetweenFromTo(LocalDate from, LocalDate to);

    @Query(value = "select *" +
            "from weather w where w.current_weather_id = " +
            "(select cw.id from current_weather cw order by last_updated limit 1)", nativeQuery = true)
    Optional<Weather> findFirstWeather();

}
