package com.golovach.weather_analyzer.exception;

import java.time.LocalDate;

public class DateIntervalException extends RuntimeException {
    public DateIntervalException(String message) {
        super(message);
    }
}
