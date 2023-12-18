package com.golovach.weather_analyzer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CurrentWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne(mappedBy = "currentWeather")
    @JsonIgnore
    private Weather weather;


    @JsonSetter("last_updated_epoch")
    private long lastUpdatedEpoch;
    @JsonSetter("last_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastUpdated;
    @JsonSetter("temp_c")
    private int tempC;
    @JsonSetter("temp_f")
    private int tempF;
    @JsonSetter("is_day")
    private int isDay;

    @JsonSetter("condition")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_condition_id", referencedColumnName = "id")
    private WeatherCondition weatherCondition;

    @JsonSetter("wind_mph")
    private double windMph;
    @JsonSetter("wind_kph")
    private double windKph;
    @JsonSetter("wind_degree")
    private double windDegree;
    @JsonSetter("wind_dir")
    private String windDir;
    @JsonSetter("pressure_mb")
    private int pressureMb;
    @JsonSetter("pressure_in")
    private double pressureIn;
    @JsonSetter("precip_mm")
    private int precipMm;
    @JsonSetter("precip_in")
    private int precipIn;
    private int humidity;
    private int cloud;
    @JsonSetter("feelslike_c")
    private double feelsLikeC;
    @JsonSetter("feelslike_f")
    private double feelsLikeF;
    @JsonSetter("vis_km")
    private double visKm;
    @JsonSetter("vis_miles")
    private double visMiles;
    private int uv;
    @JsonSetter("gust_mph")
    private double gustMph;
    @JsonSetter("gust_kph")
    private double gustKph;

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "id=" + id +
                ", lastUpdatedEpoch=" + lastUpdatedEpoch +
                ", lastUpdated=" + lastUpdated +
                ", tempC=" + tempC +
                ", tempF=" + tempF +
                ", isDay=" + isDay +
                ", weatherCondition=" + weatherCondition +
                ", windMph=" + windMph +
                ", windKph=" + windKph +
                ", windDegree=" + windDegree +
                ", windDir='" + windDir + '\'' +
                ", pressureMb=" + pressureMb +
                ", pressureIn=" + pressureIn +
                ", precipMm=" + precipMm +
                ", precipIn=" + precipIn +
                ", humidity=" + humidity +
                ", cloud=" + cloud +
                ", feelsLikeC=" + feelsLikeC +
                ", feelsLikeF=" + feelsLikeF +
                ", visKm=" + visKm +
                ", visMiles=" + visMiles +
                ", uv=" + uv +
                ", gustMph=" + gustMph +
                ", gustKph=" + gustKph +
                '}';
    }
}
