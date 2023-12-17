package com.golovach.weather_analyzer.rapidapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.golovach.weather_analyzer.entity.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ParserJson {
    public static final Logger LOGGER = LoggerFactory.getLogger(ParserJson.class);

    public Weather parseJsonToObject(String json) {
        if (json == null) {
            return null;
        }
        ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(json, Weather.class);
            return weather;
        } catch (JsonProcessingException e) {
            LOGGER.error("Convert json to object impossible: {}", e.getMessage());
        }
        return weather;
    }
}
