package com.golovach.weather_analyzer.dto;

import com.fasterxml.jackson.annotation.JsonGetter;


import java.util.Objects;


public class AverageWeatherDTO {
    private double averageTempC;
    private double averageWindMph;
    private double averagePressureMb;
    private double averageHumidity;

    @JsonGetter("average_temp_c")
    public double getAverageTempC() {
        return averageTempC;
    }

    public void setAverageTempC(double averageTempC) {
        this.averageTempC = averageTempC;
    }

    @JsonGetter("average_wind_mph")
    public double getAverageWindMph() {
        return averageWindMph;
    }

    public void setAverageWindMph(double averageWindMph) {
        this.averageWindMph = averageWindMph;
    }

    @JsonGetter("average_pressure_mb")
    public double getAveragePressureMb() {
        return averagePressureMb;
    }

    public void setAveragePressureMb(double averagePressureMb) {
        this.averagePressureMb = averagePressureMb;
    }

    @JsonGetter("average_humidity")
    public double getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageHumidity(double averageHumidity) {
        this.averageHumidity = averageHumidity;
    }

    @Override
    public String toString() {
        return "AverageWeatherDTO{" +
                "averageTempC=" + averageTempC +
                ", averageWindMph=" + averageWindMph +
                ", averagePressureMb=" + averagePressureMb +
                ", averageHumidity=" + averageHumidity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AverageWeatherDTO that = (AverageWeatherDTO) o;
        return Double.compare(that.averageTempC, averageTempC) == 0 && Double.compare(that.averageWindMph, averageWindMph) == 0 && Double.compare(that.averagePressureMb, averagePressureMb) == 0 && Double.compare(that.averageHumidity, averageHumidity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageTempC, averageWindMph, averagePressureMb, averageHumidity);
    }
}
