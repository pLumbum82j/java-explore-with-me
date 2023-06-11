package ru.practicum.models.dto;

import lombok.*;

import java.util.List;

/**
 * Результат подтверждения/отклонения заявок на участие в событии
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {
    private List<ParticipationRequestDto> confirmedRequests; //подтвержденные запросы
    private List<ParticipationRequestDto> rejectedRequests; // отклоненные запросы
}
