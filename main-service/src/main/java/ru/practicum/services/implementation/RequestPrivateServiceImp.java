package ru.practicum.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.BadRequestException;
import ru.practicum.exceptions.ConflictRequestException;
import ru.practicum.mappers.RequestMapper;
import ru.practicum.models.*;
import ru.practicum.models.dto.ParticipationRequestDto;
import ru.practicum.models.enums.EventState;
import ru.practicum.models.enums.RequestStatus;
import ru.practicum.repositories.EventRepository;
import ru.practicum.repositories.FindObjectInRepository;
import ru.practicum.repositories.RequestRepository;
import ru.practicum.services.RequestPrivateService;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class RequestPrivateServiceImp implements RequestPrivateService {

    private final RequestRepository requestRepository;
    private final FindObjectInRepository findObjectInRepository;
    private final EventRepository eventRepository;
    private final ProcessingEvents processingEvents;

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> get(Long id) {
        log.info("Получен запрос на получение всех запросов пользователя с id= " + id);
        User user = findObjectInRepository.getUserById(id);
        List<Request> requests = requestRepository.findAllByRequesterIs(user);
        return requests.stream().map(RequestMapper::requestToParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationRequestDto create(Long userId, Long eventId, HttpServletRequest httpServletRequest) {
        log.info("Получен запрос на добавление запроса от пользователя с id= {} для события с id= {}", userId, eventId);
        User user = findObjectInRepository.getUserById(userId);
        Event event = findObjectInRepository.getEventById(eventId);
        if (event.getState().equals(EventState.PUBLISHED)) {
            addEventConfirmedRequestsAndViews(event, httpServletRequest);
        } else {
            event.setViews(0L);
            event.setConfirmedRequests(0L);
        }
        checkEventState(event);
        checkEventOwner(user, event);
        checkParticipantLimit(event);
        checkEventRequestUser(userId, eventId);
        Request request;

        if (event.isRequestModeration()) {
            request = Request.builder()
                    .created(LocalDateTime.now())
                    .event(event)
                    .requester(user)
                    .status(RequestStatus.PENDING)
                    .build();
        } else {
            request = Request.builder()
                    .created(LocalDateTime.now())
                    .event(event)
                    .requester(user)
                    .status(RequestStatus.CONFIRMED)
                    .build();
        }
        try {
            eventRepository.save(event);
            return RequestMapper.requestToParticipationRequestDto(requestRepository.save(request));
        } catch (DataAccessException e) {
            throw new BadRequestException("Ошибка при работе с базой данных");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Ошибка в формировании запроса");
        }
    }

    @Override
    @Transactional
    public ParticipationRequestDto update(Long userId, Long requestId, HttpServletRequest httpServletRequest) {
        log.info("Получен запрос от пользователя с id= {} на обновление запроса с id= {}", userId, requestId);
        User user = findObjectInRepository.getUserById(userId);
        Request request = findObjectInRepository.getRequestById(requestId);
        if (!request.getRequester().equals(user)) {
            throw new ConflictRequestException("Пользователь с id= " + userId
                    + "не подавал заявку с id= " + request.getId());
        }
        request.setStatus(RequestStatus.CANCELED);
        Event event = request.getEvent();
        if (event.getState().equals(EventState.PUBLISHED)) {
            addEventConfirmedRequestsAndViews(event, httpServletRequest);
        } else {
            event.setViews(0L);
            event.setConfirmedRequests(0L);
        }
        try {
            eventRepository.save(event);
            return RequestMapper.requestToParticipationRequestDto(requestRepository.save(request));
        } catch (DataAccessException e) {
            throw new BadRequestException("Ошибка при работе с базой данных");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Ошибка в формировании запроса");
        }
    }

    private void checkEventRequestUser(Long userId, Long eventId) {
        Optional<Request> request = requestRepository.findByRequesterIdAndEventId(userId, eventId);
        if (request.isPresent()) {
            throw new ConflictRequestException("Пользователь с id= " + userId
                    + "участник события с id= " + eventId);
        }
    }

    private void checkEventOwner(User user, Event event) {
        if (Objects.equals(user.getId(), event.getInitiator().getId())) {
            throw new ConflictRequestException("Пользователь с id= " + user.getId()
                    + "владелец события с id= " + event.getId() + " и не может подавать заявку на участие");
        }
    }

    private void checkEventState(Event event) {
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictRequestException("Событие с id= " + event.getId()
                    + " не опубликовано, нельзя подавать запросы на участие");
        }
    }

    private void checkParticipantLimit(Event event) {
        if (event.getParticipantLimit() == event.getConfirmedRequests() && event.getParticipantLimit() != 0) {
            throw new ConflictRequestException("Событие с id= " + event.getId()
                    + " нельзя подавать запросы на участие, превышен лимит заявок");
        }
    }

    private void addEventConfirmedRequestsAndViews(Event event, HttpServletRequest request) {
        long count = processingEvents.confirmedRequestsForOneEvent(event, RequestStatus.CONFIRMED);
        event.setConfirmedRequests(count);
        long views = processingEvents.searchViews(event, request);
        event.setViews(views);
    }
}
