package ru.practicum.services;


import ru.practicum.models.dto.CategoryDto;
import ru.practicum.models.dto.NewCategoryDto;

public interface CategoryAdminService {

    CategoryDto create(NewCategoryDto newCategoryDto);

    void delete(Long id);

    CategoryDto update(Long id, CategoryDto newCategoryDto);
}
