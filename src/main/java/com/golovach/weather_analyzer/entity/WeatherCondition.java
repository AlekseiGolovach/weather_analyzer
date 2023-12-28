package com.golovach.weather_analyzer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
public class WeatherCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne(mappedBy = "weatherCondition")
    @JsonIgnore
    private CurrentWeather currentWeather;

    private String text;
    private String icon;
    private long code;

    @Override
    public String toString() {
        return "WeatherCondition{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherCondition that = (WeatherCondition) o;
        return code == that.code && id.equals(that.id) && Objects.equals(text, that.text) && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, icon, code);
    }
}
