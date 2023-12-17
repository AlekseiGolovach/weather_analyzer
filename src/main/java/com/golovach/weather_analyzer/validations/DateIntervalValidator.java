package com.golovach.weather_analyzer.validations;

import com.golovach.weather_analyzer.exception.DateIntervalException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateIntervalValidator {
    public boolean commonIntervalIncludesGivenInterval(LocalDate commonFirst, LocalDate givenFirst, LocalDate givenSecond, LocalDate commonSecond) {
        if (givenFirst.isAfter(givenSecond)) {
            throw new DateIntervalException("First Date can be before second Date");
        }
        if (givenFirst.isAfter(commonFirst.minusDays(1)) && givenSecond.isBefore(commonSecond.plusDays(1))) {
            return true;
        }
        throw new DateIntervalException(String.format("Date interval not found, available interval: %s %s ", commonFirst, commonSecond));
    }

}