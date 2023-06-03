package ru.practicum.exp.stat.serv.util;

import lombok.experimental.UtilityClass;
import ru.practicum.exp.stat.serv.exceptions.ValidationDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class DateFormatter {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static LocalDateTime formatDate(String date) {
        LocalDateTime newDate;
        if (date == null || date.isEmpty() || date.isBlank()) {
            throw new ValidationDateException("Дата должна быть задана");
        }
        try {
            newDate = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new ValidationDateException("Неверный формат даты");
        }
        return newDate;
    }
}