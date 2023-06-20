package ru.practicum.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

/**
 * Модель объекта ParticipationRequest Data Transfer Object
 * (Данные заявки на участие в событии)
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class ParticipationRequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    LocalDateTime created;
    Long event;
    Long id;
    Long requester;
    String status;
}
