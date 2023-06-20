package ru.practicum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.models.Category;
import ru.practicum.models.dto.CategoryDto;
import ru.practicum.models.dto.NewCategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapperMapstruct {
    CategoryMapperMapstruct INSTANCE = Mappers.getMapper(CategoryMapperMapstruct.class);

    CategoryDto toDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    Category NewtoCategory(NewCategoryDto newCategoryDto);
}
