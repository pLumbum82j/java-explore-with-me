package ru.practicum.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Модель объекта EventFull Data Transfer Object
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class EventFullDto {
    String annotation;
    CategoryDto category;
    Long confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Long id;
    UserShortDto initiator;
    LocationDto location;
    boolean paid;
    int participantLimit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn;
    boolean requestModeration;
    String state;
    String title;
    Long views;
}
