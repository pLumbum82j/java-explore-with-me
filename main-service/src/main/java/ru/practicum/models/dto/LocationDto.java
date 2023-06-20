package ru.practicum.models.dto;

import lombok.*;
import org.mapstruct.Mapper;

/**
 * Модель объекта Location Data Transfer Object
 * (Широта и долгота места проведения события)
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class LocationDto {
    float lat;
    float lon;
}
