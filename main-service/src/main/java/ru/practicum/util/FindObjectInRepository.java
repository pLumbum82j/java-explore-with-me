package ru.practicum.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.ResourceNotFoundException;
import ru.practicum.exp.stat.client.StatsClient;
import ru.practicum.models.Category;
import ru.practicum.repositories.CategoryRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindObjectInRepository {
    private final CategoryRepository categoryRepository;
    //  private final CompilationStorage compilationStorage;
    // private final EventRepository eventRepository;
    // private final RequestRepository requestRepository;
    //  private final UserRepository userRepository;
    //private final StatsClient statsClient;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Категория c id: " + id + " не найдена"));
    }

//    public Compilation getCompilationById(Long id) {
//        return compilationStorage.findById(id).orElseThrow(()
//                -> new ResourceNotFoundException("Подборка событий c id = " + id + " не найдена"));
//    }
//
//    public Event getEventById(Long id) {
//        Event event = eventRepository.findById(id).orElseThrow(()
//                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));
//
//        return event;
//    }
//
//    public Request getRequestById(Long id) {
//        return requestRepository.findById(id).orElseThrow(()
//                -> new ResourceNotFoundException("Запрос на участие c id = " + id + " не найден"));
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(()
//                -> new ResourceNotFoundException("Пользователь c id = " + id + " не найден"));
//    }
//
//    public boolean isRelatedEvent(Category category) {
//        List<Event> findEventByCategory = eventRepository.findEventByCategoryIs(category);
//        return !findEventByCategory.isEmpty();
//    }
}
