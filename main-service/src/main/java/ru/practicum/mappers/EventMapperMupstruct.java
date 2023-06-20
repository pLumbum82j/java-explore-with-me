package ru.practicum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;
import ru.practicum.models.Category;
import ru.practicum.models.Event;
import ru.practicum.models.Location;
import ru.practicum.models.User;
import ru.practicum.models.dto.*;
import ru.practicum.models.enums.EventState;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

/**
 * Утилитарный класс EventMapper для преобразования Event / EventShortDto / EventFullDto / EventState
 */
@Service
@Mapper(componentModel = "spring")
public interface EventMapperMupstruct {

    /**
     * Преобразование Event в EventShortDto
     *
     * @param event Объект Event
     * @return Преобразованный объект EventShortDto
     */
    @Mapping(target = "annotation", source = "event.annotation")
    @Mapping(target = "category", source = "categoryDto")
    @Mapping(target = "confirmedRequests", source = "event.confirmedRequests")
    @Mapping(target = "eventDate", source = "event.eventDate")
    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "initiator", source = "userShortDto")
    @Mapping(target = "paid", source = "event.paid")
    @Mapping(target = "title", source = "event.title")
    @Mapping(target = "views", source = "event.views")
    //@Mapping(target = "id", ignore = true)
    EventShortDto eventToEventShortDto(Event event, CategoryDto categoryDto, UserShortDto userShortDto);

    /**
     * Преобразование Event в EventFullDto
     *
     * @param event Объект Event
     * @return Преобразованный объект EventFullDto
     */
    @Mapping(target = "annotation", source = "event.annotation")
    @Mapping(target = "category", source = "categoryDto")
    @Mapping(target = "confirmedRequests", source = "event.confirmedRequests")
    @Mapping(target = "createdOn", source = "event.createdOn")
    @Mapping(target = "description", source = "event.description")
    @Mapping(target = "eventDate", source = "event.eventDate")
    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "initiator", source = "userShortDto")
    @Mapping(target = "location", source = "locationDto")
    @Mapping(target = "paid", source = "event.paid")
    @Mapping(target = "participantLimit", source = "event.participantLimit")
    @Mapping(target = "publishedOn", source = "event.publishedOn")
    @Mapping(target = "requestModeration", source = "event.requestModeration")
    @Mapping(target = "state", source = "event.state")
    @Mapping(target = "title", source = "event.title")
    @Mapping(target = "views", source = "event.views")
    EventFullDto eventToEventFullDto(Event event, CategoryDto categoryDto, UserShortDto userShortDto, LocationDto locationDto);


        /**
     * Преобразование нескольких параметров в Event
     *
     * @param newEventDto       Объект NewEventDto
     * @param user              Объект User
     * @param category          Объект Category
     * @param views             Количество просмотрев события
     * @param confirmedRequests Количество одобренных заявок на участие в данном событии
     * @return Преобразованный объект Event
     */
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "annotation", source = "newEventDto.annotation")
        @Mapping(target = "category", source = "category")
        @Mapping(target = "confirmedRequests", source = "confirmedRequests")
        @Mapping(target = "createdOn", source = "createdOn")
        @Mapping(target = "description", source = "newEventDto.description")
        @Mapping(target = "eventDate", source = "newEventDto.eventDate")
        @Mapping(target = "initiator", source = "user")
        @Mapping(target = "location", source = "location")
        @Mapping(target = "paid", source = "newEventDto.paid")
        @Mapping(target = "participantLimit", source = "newEventDto.participantLimit")
        @Mapping(target = "publishedOn", source = "publishedOn")
        @Mapping(target = "requestModeration", source = "newEventDto.requestModeration")
        @Mapping(target = "state", source = "eventState")
        @Mapping(target = "title", source = "newEventDto.title")
        @Mapping(target = "views", source = "views")
    Event newEventDtoToCreateEvent(NewEventDto newEventDto, User user,
                                   Category category, Long views, Long confirmedRequests, Location location,
                                   EventState eventState, LocalDateTime createdOn, LocalDateTime publishedOn);

    @Mapping(target = "annotation", source = "event.annotation")
    @Mapping(target = "category", source = "categoryDto")
    @Mapping(target = "eventDate", source = "event.eventDate")
    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "initiator", source = "userShortDto")
    @Mapping(target = "title", source = "event.title")
    EventCommentDto eventToEventCommentDto(Event event, CategoryDto categoryDto, UserShortDto userShortDto);
}
