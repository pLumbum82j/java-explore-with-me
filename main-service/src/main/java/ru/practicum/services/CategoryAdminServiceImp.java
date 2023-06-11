package ru.practicum.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.BadRequestException;
import ru.practicum.exceptions.ConflictNameCategoryException;
import ru.practicum.mappers.CategoryMapper;
import ru.practicum.models.Category;
import ru.practicum.models.dto.CategoryDto;
import ru.practicum.models.dto.NewCategoryDto;
import ru.practicum.repositories.CategoryRepository;
import ru.practicum.repositories.FindObjectInRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryAdminServiceImp implements CategoryAdminService {

    private final CategoryRepository categoryRepository;
    private final FindObjectInRepository findObjectInRepository;

    @Override
    @Transactional
    public CategoryDto create(NewCategoryDto newCategoryDto) {
        Category category = CategoryMapper.newCategoryDtoToCategory(newCategoryDto);
        log.info("Получен запрос на добавление категории с названием: {}", newCategoryDto.getName());
        return getCategoryDto(category, newCategoryDto.getName());
    }

    @Override
    @Transactional
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category category = findObjectInRepository.getCategoryById(id);
        category.setId(id);
        category.setName(categoryDto.getName());
        log.info("Получен запрос на обновлении категории c id: {}", id);
        return getCategoryDto(category, category.getName());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = findObjectInRepository.getCategoryById(id);
//        if (findObjectInRepository.isRelatedEvent(category)) {
//            throw new ConflictDeleteException("Существуют события, связанные с категорией " + category.getName());
//        }
        log.info("Получен запрос на удаление категории c id: {}", id);
        categoryRepository.delete(category);
    }

    private CategoryDto getCategoryDto(Category category, String name) {
        try {
            return CategoryMapper.categoryToCategoryDto(categoryRepository.save(category));
        } catch (DataIntegrityViolationException e) {
            log.warn("Категория с именем: {} уже существует", name);
            throw new ConflictNameCategoryException("Имя категории " + name + " уже есть в базе");
        } catch (Exception e) {
            log.warn("Запрос на добавлении категории {} составлен не корректно", name);
            throw new BadRequestException("Запрос на добавлении категории " + name + " составлен неправильно");
        }
    }
}