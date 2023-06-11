package ru.practicum.services;


import ru.practicum.models.dto.CategoryDto;

import java.util.List;

public interface CategoryPublicService {
    List<CategoryDto> get(int from, int size);

    CategoryDto get(Long catId);
}
