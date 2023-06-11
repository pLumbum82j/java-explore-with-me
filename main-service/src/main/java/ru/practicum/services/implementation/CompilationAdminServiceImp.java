package ru.practicum.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mappers.CompilationMapper;
import ru.practicum.models.Compilation;
import ru.practicum.models.Event;
import ru.practicum.models.dto.CompilationDto;
import ru.practicum.models.dto.NewCompilationDto;
import ru.practicum.models.dto.UpdateCompilationRequest;
import ru.practicum.repositories.CompilationRepository;
import ru.practicum.repositories.EventRepository;
import ru.practicum.repositories.FindObjectInRepository;
import ru.practicum.services.CompilationAdminService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class CompilationAdminServiceImp implements CompilationAdminService {

    private final CompilationRepository compilationRepository;
    private final FindObjectInRepository findObjectInRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public CompilationDto create(NewCompilationDto newCompilationDto) {
        log.info("Получен запрос на добавление подборки событий {}", newCompilationDto);
        Set<Event> events = new HashSet<>();
        if (newCompilationDto.getEvents() != null && !newCompilationDto.getEvents().isEmpty()) {
            events = addEvents(newCompilationDto.getEvents());
        }
        Compilation compilation = CompilationMapper.newCompilationDtoToCompilationAndEvents(newCompilationDto, events);
        return CompilationMapper.compilationToCompilationDto(compilationRepository.save(compilation));
    }

    @Override
    @Transactional
    public CompilationDto update(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        log.info("Получен запрос на обновление подборки событий по id= {}", compId);
        Compilation newCompilation = findObjectInRepository.getCompilationById(compId);
        Set<Event> events;
        if (updateCompilationRequest.getEvents() != null) {
            events = addEvents(updateCompilationRequest.getEvents());
            newCompilation.setEvents(events);
        }
        if (updateCompilationRequest.getPinned() != null) {
            newCompilation.setPinned(updateCompilationRequest.getPinned());
        }
        if (updateCompilationRequest.getTitle() != null && updateCompilationRequest.getTitle().isBlank()) {
            newCompilation.setTitle(updateCompilationRequest.getTitle());
        }
        return CompilationMapper.compilationToCompilationDto(compilationRepository.save(newCompilation));
    }

    @Override
    @Transactional
    public void delete(Long compId) {
        log.info("Получен запрос на удаление подборки событий по id= {}", compId);
        findObjectInRepository.getCompilationById(compId);
        compilationRepository.deleteById(compId);
    }

    private Set<Event> addEvents(List<Long> eventsIds) {
        return eventRepository.findAllByIdIsIn(eventsIds);
    }
}
