package ru.practicum.models;

import lombok.*;
import org.mapstruct.Mapper;

import javax.persistence.Embeddable;

/**
 * Модель объекта Location
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class Location {
    private float lat;
    private float lon;
}
