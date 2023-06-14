package ru.practicum.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.BadRequestException;
import ru.practicum.exceptions.ResourceNotFoundException;
import ru.practicum.exp.stat.client.StatsClient;
import ru.practicum.exp.stat.dto.HitDto;
import ru.practicum.mappers.EventMapper;
import ru.practicum.models.Event;
import ru.practicum.models.enums.EventState;
import ru.practicum.models.dto.EventFullDto;
import ru.practicum.models.dto.EventShortDto;
import ru.practicum.models.enums.RequestStatus;
import ru.practicum.repositories.EventRepository;
import ru.practicum.services.EventPublicService;
import ru.practicum.util.DateFormatter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventPublicServiceImp implements EventPublicService {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final EventRepository eventRepository;
    private final ProcessingEvents processingEvents;
    private final StatsClient statsClient;
    // @Value("${app.name}")
    private String appName = "main-service";


    @Override
    public List<EventShortDto> get(String text, List<Long> categories, Boolean paid, String rangeStart,
                                   String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size, HttpServletRequest request) {
        log.info("Получен запрос на получение всех событий (публичный)");
        checkDateTime(rangeStart == null ? null : LocalDateTime.parse(rangeStart, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)),
                rangeEnd == null ? null : LocalDateTime.parse(rangeEnd, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        HitDto hitDto = HitDto.builder()
                .app(appName)
                .uri("/events")
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .build();
        statsClient.hitRequest(hitDto);
        text = text == null ? "" : text;
        List<Event> events = eventRepository.findAllByPublic(text, categories, paid, rangeStart, rangeEnd, sort, from, size);
        if (events.isEmpty()) {
            return Collections.emptyList();
        }
        List<Event> eventsAddViews = processingEvents.addViewsInEventsList(events, request);
        List<Event> newEvents = processingEvents.confirmedRequests(eventsAddViews);
        if (!onlyAvailable) {
            return newEvents.stream().filter(e -> e.getParticipantLimit() >= e.getConfirmedRequests())
                    .map(EventMapper::eventToeventShortDto).collect(Collectors.toList());
        }
        return newEvents.stream().map(EventMapper::eventToeventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto get(Long id, HttpServletRequest request) {
        //14.06.2023
        log.info("Получен запрос на получение события по id= {} (публичный)", id);
        HitDto hitDto = createHitDtoToStats(request);
        statsClient.hitRequest(hitDto);
        Event event = eventRepository.findEventByIdAndStateIs(id, EventState.PUBLISHED).orElseThrow(()
                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));
        addEventConfirmedRequestsAndViews(event, request);
        return EventMapper.eventToEventFullDto(event);
    }

    private HitDto createHitDtoToStats(HttpServletRequest request) {
        HitDto hitDto = HitDto.builder()
                .app(appName)
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .build();
        return hitDto;
    }

    private void addEventConfirmedRequestsAndViews(Event event, HttpServletRequest request) {
        long count = processingEvents.confirmedRequestsForOneEvent(event, RequestStatus.CONFIRMED);
        event.setConfirmedRequests(count);
        long views = processingEvents.searchViews(event, request);
        event.setViews(views);
    }

    private void checkDateTime(LocalDateTime start, LocalDateTime end) {
        if (start == null) {
            start = LocalDateTime.now().minusYears(100);
        }
        if (end == null) {
            end = LocalDateTime.now();
        }
        if (start.isAfter(end)) {
            throw new BadRequestException("Некорректный запрос. Дата окончания события задана позже даты старта");
        }
    }
}
