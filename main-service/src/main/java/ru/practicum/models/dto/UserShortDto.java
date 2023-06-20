package ru.practicum.models.dto;

import lombok.*;
import org.mapstruct.Mapper;

/**
 * Модель объекта UserShort Data Transfer Object
 * (Пользователь (краткая информация))
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class UserShortDto {
    Long id;
    String name;
}
