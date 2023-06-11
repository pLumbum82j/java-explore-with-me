package ru.practicum.services;


import ru.practicum.models.dto.CompilationDto;

import java.util.List;

public interface CompilationPublicService {

    List<CompilationDto> get(Boolean pinned, int from, int size);

    CompilationDto get(Long id);
}
