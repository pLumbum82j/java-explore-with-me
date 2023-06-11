package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.models.dto.CompilationDto;
import ru.practicum.models.dto.NewCompilationDto;
import ru.practicum.models.dto.UpdateCompilationRequest;
import ru.practicum.services.CompilationAdminService;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationAdminController {
    private final CompilationAdminService compilationAdminService;


    /**
     * Добавление новой подборки (подборка может не содержать событий)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto create(@Validated @RequestBody NewCompilationDto newCompilationDto) {
        return compilationAdminService.create(newCompilationDto);
    }

    /**
     * обновить информацию о подборке
     */
    @PatchMapping("/{compId}")
    public CompilationDto update(@PathVariable Long compId,
                                 @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return compilationAdminService.update(compId, updateCompilationRequest);
    }

    /**
     * Удаление подборки
     */
    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long compId) {
        compilationAdminService.delete(compId);
    }
}
