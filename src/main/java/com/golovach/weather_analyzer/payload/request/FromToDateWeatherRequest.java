package com.golovach.weather_analyzer.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.golovach.weather_analyzer.annotations.ValidDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
