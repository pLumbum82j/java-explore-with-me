package ru.practicum.services;


import ru.practicum.models.dto.CategoryDto;
import ru.practicum.models.dto.NewCategoryDto;

public interface CategoryAdminService {

    CategoryDto create(NewCategoryDto newCategoryDto);

    CategoryDto update(Long id, CategoryDto newCategoryDto);

    void delete(Long id);
}
