package ru.practicum.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Модель объекта EventShort Data Transfer Object
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class EventShortDto {
    String annotation;
    CategoryDto category;
    Long confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Long id;
    UserShortDto initiator;
    boolean paid;
    String title;
    Long views;
}
