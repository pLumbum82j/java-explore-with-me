package ru.practicum.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;
import ru.practicum.models.Request;
import ru.practicum.models.dto.ParticipationRequestDto;

/**
 * Утилитарный класс RequestMapper для преобразования Request / ParticipationRequestDto
 */
@Service
@Mapper(componentModel = "spring")
public interface RequestMapperMupstrict {

    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Преобразование Request в ParticipationRequestDto
     *
     * @param request Объект Request
     * @return Преобразованный объект ParticipationRequestDto
     */
    @Mapping(target = "event", source = "request.event.id")
    // @Mapping(target = "created", source = "request.created")
    @Mapping(target = "requester", source = "request.requester.id")
       ParticipationRequestDto requestToParticipationRequestDto(Request request);
}
