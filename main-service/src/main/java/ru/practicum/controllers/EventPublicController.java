package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.models.dto.EventFullDto;
import ru.practicum.models.dto.EventShortDto;
import ru.practicum.services.EventPublicService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventPublicController {

    private final EventPublicService eventPublicService;

    @GetMapping()
    List<EventShortDto> get(@RequestParam(required = false) String text,
                            @RequestParam(required = false) List<Long> categories,
                            @RequestParam(required = false) Boolean paid,
                            @RequestParam(required = false) String rangeStart,
                            @RequestParam(required = false) String rangeEnd,
                            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                            @RequestParam(defaultValue = "EVENT_DATE") String sort,
                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                            @Positive @RequestParam(defaultValue = "10") Integer size,
                            HttpServletRequest request) {
        return eventPublicService.get(text, categories, paid, rangeStart, rangeEnd,
                onlyAvailable, sort, from, size, request);
    }

    @GetMapping("/{id}")
    EventFullDto get(@PathVariable Long id, HttpServletRequest request) {
        return eventPublicService.get(id, request);
    }
}
