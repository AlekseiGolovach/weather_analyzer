package com.golovach.weather_analyzer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class WeatherLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne(mappedBy = "weatherLocation")
    @JsonIgnore
    private Weather weather;

    private String name;
    private String region;
    private String country;
    @JsonSetter("lat")
    private double latitude; //широта
    @JsonSetter("lon")
    private double longitude;//долгота
    @JsonSetter("tz_id")
    private String timeZoneId;
    @JsonSetter("localtime_epoch")
    private long localtimeEpoch;
    @JsonSetter("localtime")
    @JsonFormat(pattern = "yyyy-MM-dd H:mm")
    private LocalDateTime localDateTime;

    @Override
    public String toString() {
        return "WeatherLocation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timeZoneId='" + timeZoneId + '\'' +
                ", localtimeEpoch=" + localtimeEpoch +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
