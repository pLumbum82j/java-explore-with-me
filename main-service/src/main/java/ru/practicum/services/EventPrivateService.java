package ru.practicum.services;


import ru.practicum.models.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventPrivateService {
    List<EventShortDto> getAllPrivateEventsByUserId(Long userId, int from, int size, HttpServletRequest request);

    EventFullDto addPrivateEventByUserId(Long userId, NewEventDto newEventDto);

    EventFullDto getPrivateEventByIdAndByUserId(Long userId, Long eventId, HttpServletRequest request);

    EventFullDto updatePrivateEventByIdAndByUserId(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest, HttpServletRequest request);

    List<ParticipationRequestDto> getAllPrivateEventsByRequests(Long userId, Long eventId, HttpServletRequest request);

    EventRequestStatusUpdateResult updateEventRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequest, HttpServletRequest request);
}
