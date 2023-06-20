package ru.practicum.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import ru.practicum.models.Category;
import ru.practicum.models.dto.CategoryDto;
import ru.practicum.models.dto.NewCategoryDto;

@Service
@Mapper(componentModel = "spring")
public interface CategoryMapperMapstruct {
    // CategoryMapperMapstruct INSTANCE = Mappers.getMapper(CategoryMapperMapstruct.class);

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);

    Category newCategoryDtoToCategory(NewCategoryDto newCategoryDto);
}
