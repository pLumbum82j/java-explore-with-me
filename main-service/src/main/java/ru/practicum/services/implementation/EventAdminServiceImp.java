package ru.practicum.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.BadRequestException;
import ru.practicum.exceptions.ForbiddenEventException;
import ru.practicum.exceptions.ResourceNotFoundException;
import ru.practicum.util.DateFormatter;
import ru.practicum.mappers.EventMapper;
import ru.practicum.mappers.LocationMapper;
import ru.practicum.models.Category;
import ru.practicum.models.Event;
import ru.practicum.models.enums.EventState;
import ru.practicum.models.dto.EventFullDto;
import ru.practicum.models.dto.UpdateEventAdminRequest;
import ru.practicum.models.enums.ActionState;
import ru.practicum.repositories.EventRepository;
import ru.practicum.repositories.FindObjectInRepository;
import ru.practicum.services.EventAdminService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventAdminServiceImp implements EventAdminService {

    private final EventRepository eventRepository;
    private final FindObjectInRepository findObjectInRepository;
    private final ProcessingEvents processingEvents;


    @Override
    @Transactional(readOnly = true)
    public List<EventFullDto> get(List<Long> users, List<String> states, List<Long> categories,
                                  String rangeStart, String rangeEnd, int from, int size, HttpServletRequest request) {
        log.info("Получен запрос на поиск всех событый (администратором)");
        PageRequest page = PageRequest.of(from, size);
        List<Event> events = new ArrayList<>();
        LocalDateTime newRangeStart = null;
        if (rangeStart != null) {
            newRangeStart = DateFormatter.formatDate(rangeStart);
        }
        LocalDateTime newRangeEnd = null;
        if (rangeEnd != null) {
            newRangeEnd = DateFormatter.formatDate(rangeEnd);
        }

        if (states != null) {
            events = eventRepository.findAllByAdmin(users, states, categories, newRangeStart, newRangeEnd, from, size);
            List<Event> eventsAddViews = processingEvents.addViewsInEventsList(events, request);
            List<Event> newEvents = processingEvents.confirmedRequests(eventsAddViews);
            return newEvents.stream().map(EventMapper::eventToEventFullDto).collect(Collectors.toList());
        } else {
            //14.06.2023
            //  events = eventRepository.findAllByAdminAndState(users, categories, newRangeStart, newRangeEnd, from, size);
            // events = eventRepository.findAllByAdminAndState(users, categories, newRangeStart, newRangeEnd, page);
            events = eventRepository.findAllByAdminAndState(users,null, categories, newRangeStart, newRangeEnd, page);
             List<Event> eventsAddViews = processingEvents.addViewsInEventsList(events, request);
            List<Event> newEvents = processingEvents.confirmedRequests(eventsAddViews);
            //return events.stream().map(EventMapper::eventToEventFullDto).collect(Collectors.toList());
            return newEvents.stream().map(EventMapper::eventToEventFullDto).collect(Collectors.toList());
        }
    }

    @Override
    @Transactional
    public EventFullDto update(Long eventId, UpdateEventAdminRequest updateEvent, HttpServletRequest request) {
        log.info("Получен запрос на обновление события с id= {} (администратором)", eventId);
        Event event = findObjectInRepository.getEventById(eventId);
        eventAvailability(event);
        if (updateEvent.getEventDate() != null) {
            checkEventDate(DateFormatter.formatDate(updateEvent.getEventDate()));
        }
        if (updateEvent.getAnnotation() != null && !updateEvent.getAnnotation().isBlank()) {
            event.setAnnotation(updateEvent.getAnnotation());
        }
        if (updateEvent.getCategory() != null) {
            Category category = findObjectInRepository.getCategoryById(updateEvent.getCategory());
            event.setCategory(category);
        }
        if (updateEvent.getDescription() != null && !updateEvent.getDescription().isBlank()) {
            event.setDescription(updateEvent.getDescription());
        }
        if (updateEvent.getEventDate() != null) {
            event.setEventDate(DateFormatter.formatDate(updateEvent.getEventDate()));
        }
        if (updateEvent.getLocation() != null) {
            event.setLocation(LocationMapper.locationDtoToLocation(updateEvent.getLocation()));
        }
        if (updateEvent.getPaid() != null) {
            event.setPaid(updateEvent.getPaid());
        }
        if (updateEvent.getParticipantLimit() != null) {
            event.setParticipantLimit(updateEvent.getParticipantLimit());
        }
        if (updateEvent.getRequestModeration() != null) {
            event.setRequestModeration(updateEvent.getRequestModeration());
        }
        if (updateEvent.getStateAction() != null) {
            if (!event.getState().equals(EventState.PUBLISHED) && updateEvent.getStateAction().equals(ActionState.PUBLISH_EVENT)) {
                event.setPublishedOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            } else if (event.getPublishedOn() == null) {
                event.setPublishedOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)); //????
            }
            event.setState(determiningTheStatusForEvent(updateEvent.getStateAction()));
        }
        if (updateEvent.getTitle() != null && !updateEvent.getTitle().isBlank()) {
            event.setTitle(updateEvent.getTitle());
        }
        if (event.getState().equals(EventState.PUBLISHED)) {
            addEventConfirmedRequestsAndViews(event, request);
        } else {
            event.setViews(0L);
            event.setConfirmedRequests(0L);
        }
        try {
            return EventMapper.eventToEventFullDto(eventRepository.save(event));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("База данных недоступна");
        } catch (Exception e) {
            throw new BadRequestException("Запрос на добавлении события " + event + " составлен не корректно ");
        }
    }

    private LocalDateTime checkEventDate(LocalDateTime eventDate) {
        LocalDateTime timeNow = LocalDateTime.now().plusHours(1L);
        if (eventDate != null && eventDate.isBefore(timeNow)) {
            throw new BadRequestException("Событие должно содержать дату, которая еще не наступила. " +
                    "Value: " + eventDate);
        }
        return timeNow;
    }

    private EventState determiningTheStatusForEvent(ActionState stateAction) {
        if (stateAction.equals(ActionState.SEND_TO_REVIEW)) {
            return EventState.PENDING;
        } else if (stateAction.equals(ActionState.CANCEL_REVIEW)) {
            return EventState.CANCELED;
        } else if (stateAction.equals(ActionState.PUBLISH_EVENT)) {
            return EventState.PUBLISHED;
        } else if (stateAction.equals(ActionState.REJECT_EVENT)) {
            return EventState.CANCELED;
        } else {
            throw new BadRequestException("Статус не соответствует модификатору доступа");
        }
    }

    private void eventAvailability(Event event) {
        if (event.getState().equals(EventState.PUBLISHED) || event.getState().equals(EventState.CANCELED)) {
            throw new ForbiddenEventException("Статус события не позволяет редактировать событие, статус: " + event.getState());
        }
    }

    private void addEventConfirmedRequestsAndViews(Event event, HttpServletRequest request) {
        //     long count = processingEvents.confirmedRequestsForOneEvent(event, RequestStatus.CONFIRMED);
        //     event.setConfirmedRequests(count);
        long views = processingEvents.searchViews(event, request);
        event.setViews(views);
    }
}
