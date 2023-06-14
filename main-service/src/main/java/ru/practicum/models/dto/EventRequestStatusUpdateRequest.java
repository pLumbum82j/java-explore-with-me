package ru.practicum.models.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.models.enums.RequestStatus;

import java.util.List;

@Value
@Builder
public class EventRequestStatusUpdateRequest { // Изменение статуса запроса на участие в событии текущего пользователя
    List<Long> requestIds; // Идентификаторы запросов на участие в событии текущего пользователя
    RequestStatus status;
}
