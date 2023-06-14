package ru.practicum.exp.stat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

/**
 * Модель объекта ViewStats Data Transfer Object
 */
@Value
@Builder
@AllArgsConstructor
public class ViewStatsDto {

    String app;
    String uri;
    Long hits;
}
