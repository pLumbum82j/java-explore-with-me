package ru.practicum.services;


import ru.practicum.models.dto.EventFullDto;
import ru.practicum.models.dto.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventPublicService {

    List<EventShortDto> get(String text, List<Long> categories, Boolean paid, String rangeStart, String rangeEnd,
                            boolean onlyAvailable, String sort, Integer from, Integer size, HttpServletRequest request);

    EventFullDto get(Long id, HttpServletRequest request);
}
