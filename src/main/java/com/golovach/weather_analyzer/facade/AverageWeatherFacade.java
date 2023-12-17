package com.golovach.weather_analyzer.facade;

import com.golovach.weather_analyzer.dto.AverageWeatherDTO;
import com.golovach.weather_analyzer.entity.CurrentWeather;
import com.golovach.weather_analyzer.entity.Weather;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AverageWeatherFacade {
    public AverageWeatherDTO listWeatherToAverageWeatherDTO(List<Weather> weatherList) {
        double[] averageTempC = new double[weatherList.size()];
        double[] averageWindMph = new double[weatherList.size()];
        double[] averageHumidity = new double[weatherList.size()];
        double[] averagePressureMb = new double[weatherList.size()];
        int index = 0;
        for (Weather w :
                weatherList) {
            CurrentWeather currentWeather =w.getCurrentWeather();
            averageTempC[index] = currentWeather.getTempC();
            averageWindMph[index] = currentWeather.getWindMph();
            averageHumidity[index] = currentWeather.getHumidity();
            averagePressureMb[index] = currentWeather.getPressureMb();
            index++;
        }

        AverageWeatherDTO averageWeatherDTO = new AverageWeatherDTO();

        averageWeatherDTO.setAverageTempC(average(averageTempC));
        averageWeatherDTO.setAverageWindMph(average(averageWindMph));
        averageWeatherDTO.setAveragePressureMb(average(averagePressureMb));
        averageWeatherDTO.setAverageHumidity(average(averageHumidity));

        return averageWeatherDTO;
    }

    private double average(double[] doubles) {
        double sum = 0;
        for (int i = 0; i < doubles.length; i++) {
            sum = sum + doubles[i];
        }
        double result = sum / doubles.length;
        return Math.round(result * 100.0) / 100.0;
    }
}



