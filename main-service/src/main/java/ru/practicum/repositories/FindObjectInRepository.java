package ru.practicum.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.ResourceNotFoundException;
import ru.practicum.models.Category;
import ru.practicum.models.User;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindObjectInRepository {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Категория c id: " + id + " не найдена"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Пользователь c id: " + id + " не найден"));
    }
}
