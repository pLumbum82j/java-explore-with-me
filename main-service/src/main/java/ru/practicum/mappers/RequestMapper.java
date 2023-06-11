package ru.practicum.mappers;


import lombok.experimental.UtilityClass;
import ru.practicum.models.Request;
import ru.practicum.models.dto.ParticipationRequestDto;

@UtilityClass
public class RequestMapper {
    public ParticipationRequestDto requestToParticipationRequestDto(Request request) {
        return ParticipationRequestDto.builder()
                .created(request.getCreated())
                .event(request.getEvent().getId())
                .id(request.getId())
                .requester(request.getRequester().getId())
                .status(request.getStatus().name())
                .build();
    }
}
