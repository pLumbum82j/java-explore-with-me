package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.practicum.models.dto.*;
import ru.practicum.services.EventPrivateService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class EventPrivateController {
    private final EventPrivateService eventPrivateService;

    @GetMapping()
    List<EventShortDto> getAllPrivateEventsByUser(@PathVariable Long userId,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "10") Integer size,
                                                  HttpServletRequest request) {
        return eventPrivateService.getAllPrivateEventsByUserId(userId, from, size, request);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    EventFullDto addPrivateEventByUserId(@PathVariable Long userId,
                                         @Validated @RequestBody NewEventDto newEventDto) {
        return eventPrivateService.addPrivateEventByUserId(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    EventFullDto getPrivateEventByIdAndByUserId(@PathVariable Long userId,
                                                @PathVariable Long eventId,
                                                HttpServletRequest request) {
        return eventPrivateService.getPrivateEventByIdAndByUserId(userId, eventId, request);
    }

    @PatchMapping("/{eventId}")
    EventFullDto updatePrivateEventByIdAndByUserId(@PathVariable Long userId,
                                                   @PathVariable Long eventId,
                                                   @Validated @RequestBody UpdateEventUserRequest updateEventUserRequest,
                                                   HttpServletRequest request) {
        return eventPrivateService.updatePrivateEventByIdAndByUserId(userId, eventId, updateEventUserRequest, request);
    }

    @GetMapping("/{eventId}/requests")
    List<ParticipationRequestDto> getAllPrivateEventsByRequests(@PathVariable Long userId,
                                                                @PathVariable Long eventId,
                                                                HttpServletRequest request) {
        return eventPrivateService.getAllPrivateEventsByRequests(userId, eventId, request);
    }

    @PatchMapping("/{eventId}/requests")
    EventRequestStatusUpdateResult updateEventRequestStatus(@PathVariable Long userId,
                                                            @PathVariable Long eventId,
                                                            @RequestBody EventRequestStatusUpdateRequest eventRequest,
                                                            HttpServletRequest request) {
        return eventPrivateService.updateEventRequestStatus(userId, eventId, eventRequest, request);
    }
}
