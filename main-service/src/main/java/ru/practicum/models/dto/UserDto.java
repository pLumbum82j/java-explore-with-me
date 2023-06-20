package ru.practicum.models.dto;

import lombok.*;
import org.mapstruct.Mapper;

/**
 * Модель объекта User Data Transfer Object
 * (Пользователь)
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class UserDto {
    String email;
    Long id;
    String name;
}
