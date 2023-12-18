package com.golovach.weather_analyzer.payload.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.golovach.weather_analyzer.annotations.ValidDate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class FromToDateWeatherRequest {

    @ValidDate(message = "Please enter first Date in this format dd-MM-yyyy")
    @NotNull(message = "Please enter first Date")
    @JsonSetter("from")
    private String fromDate;

    @ValidDate(message = "Please enter second Date in this format dd-MM-yyyy")
    @NotNull(message = "Please enter Second Date")
    @JsonSetter("to")
    private String toDate;

}
