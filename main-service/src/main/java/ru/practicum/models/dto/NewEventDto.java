package ru.practicum.models.dto;

import lombok.*;

import javax.validation.constraints.*;

@Builder
@Value
public class NewEventDto {
    @NotBlank(message = "Поле annotation должно быть заполнено")
    @Size(min = 20, max = 2000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 2000")
    String annotation;
    @NotNull(message = "category не должно быть пустым")
    Long category;
    @NotBlank(message = "Поле description должно быть заполнено")
    @Size(min = 20, max = 7000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 7000")
    String description;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String eventDate;
    LocationDto location;
    //@org.springframework.beans.factory.annotation.Value("false")
    Boolean paid;
    // @org.springframework.beans.factory.annotation.Value("0")
    @PositiveOrZero
    Integer participantLimit;
    //@org.springframework.beans.factory.annotation.Value("true")
    boolean requestModeration;
    @NotBlank(message = "Поле title должно быть заполнено")
    @Size(min = 3, max = 120, message = "Минимальное кол-во символов для описания: 3. Максимальное: 120")
    String title;
}
