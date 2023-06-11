package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.models.dto.ParticipationRequestDto;
import ru.practicum.services.RequestPrivateService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")

public class RequestPrivateController {

    private final RequestPrivateService requestPrivateService;


    /**
     * Информация о заявках текущего пользователя на участие в чужих событиях
     */
    @GetMapping
    List<ParticipationRequestDto> get(@PathVariable Long userId) {
        return requestPrivateService.get(userId);
    }

    /**
     * Добавление запроса от текущего пользователя на участие в событии
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ParticipationRequestDto create(@PathVariable Long userId,
                                                @NotNull @RequestParam Long eventId,
                                                HttpServletRequest request) {
        return requestPrivateService.create(userId, eventId, request);
    }

    /**
     * Отмена своего запроса на участие в событии
     */
    @PatchMapping("/{requestId}/cancel")
    ParticipationRequestDto update(@PathVariable Long userId,
                                              @PathVariable Long requestId,
                                              HttpServletRequest request) {
        return requestPrivateService.update(userId, requestId, request);
    }
}
