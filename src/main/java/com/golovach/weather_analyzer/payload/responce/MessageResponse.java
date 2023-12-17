package com.golovach.weather_analyzer.payload.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class MessageResponse {
    private String message;
}
