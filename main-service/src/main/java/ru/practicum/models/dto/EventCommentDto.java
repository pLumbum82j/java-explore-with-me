package ru.practicum.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

/**
 * Модель объекта EventComment Data Transfer Object
 * (Комментарий к событию)
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class EventCommentDto {
    String annotation;
    CategoryDto category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Long id;
    UserShortDto initiator;
    String title;
}
