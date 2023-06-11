package ru.practicum.models.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Size;
import java.util.List;

@Value
@Builder
public class CompilationDto {
    List<EventShortDto> events;
    Long id;
    boolean pinned;
    String title;
}
