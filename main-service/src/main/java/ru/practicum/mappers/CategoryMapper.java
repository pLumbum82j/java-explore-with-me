package ru.practicum.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.models.Category;
import ru.practicum.models.dto.CategoryDto;
import ru.practicum.models.dto.NewCategoryDto;


@UtilityClass
public class CategoryMapper {
    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    public CategoryDto categoryToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category newCategoryDtoToCategory(NewCategoryDto newCategoryDto) {
        return Category.builder()
                .name(newCategoryDto.getName())
                .build();
    }
}
