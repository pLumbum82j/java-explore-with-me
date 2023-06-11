package ru.practicum.services;


import ru.practicum.models.dto.CompilationDto;
import ru.practicum.models.dto.NewCompilationDto;
import ru.practicum.models.dto.UpdateCompilationRequest;

public interface CompilationAdminService {
    CompilationDto create(NewCompilationDto newCompilationDto);

    CompilationDto update(Long compId, UpdateCompilationRequest updateCompilationRequest);

    void delete(Long compId);
}
