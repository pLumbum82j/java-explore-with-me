package ru.practicum.models.dto;

import lombok.*;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Модель объекта Compilation Data Transfer Object
 * (Подборка событий)
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class CompilationDto {
    List<EventShortDto> events;
    Long id;
    boolean pinned;
    String title;
}
