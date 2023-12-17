package com.golovach.weather_analyzer.rapidapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class RapidApi { //https://rapidapi.com/weatherapi/api/weatherapi-com
    public static final Logger LOGGER = LoggerFactory.getLogger(RapidApi.class);

    public String pollWeatherJson(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=53.903675%2C%2027.554477"))
                .header("X-RapidAPI-Key", "254d009c34mshb489c2b550f6652p109591jsn28b16e4667f2")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                LOGGER.error(String.format("rapidapi.com exception: status: %s, message: %s", response.statusCode(), response.body()));
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.error("rapidapi.com not available {}", e.getMessage());
        }
        return null;
    }






}
