package ru.practicum.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.exceptions.BadRequestException;
import ru.practicum.util.DateFormatter;
import ru.practicum.models.Category;
import ru.practicum.models.Event;
import ru.practicum.models.enums.EventState;
import ru.practicum.models.User;
import ru.practicum.models.dto.EventFullDto;
import ru.practicum.models.dto.EventShortDto;
import ru.practicum.models.dto.NewEventDto;
import ru.practicum.models.enums.EventStateDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class EventMapper {

    public EventShortDto eventToeventShortDto(Event event) {
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.categoryToCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.userToUserShortDto(event.getInitiator()))
                .paid(event.isPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public EventFullDto eventToEventFullDto(Event event) {
        return EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.categoryToCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.userToUserShortDto(event.getInitiator()))
                .location(LocationMapper.locationToLocationDto(event.getLocation()))
                .paid(event.isPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.isRequestModeration())
                .state(event.getState().name())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static Event newEventDtoToCreateEvent(NewEventDto newEventDto, User user, Category category, Long views,
                                                 Long confirmedRequests) {
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .category(category)
                .confirmedRequests(confirmedRequests)
                .createdOn(dateTime)
                .description(newEventDto.getDescription())
                .eventDate(DateFormatter.formatDate(newEventDto.getEventDate()))
                .initiator(user)
                .location(LocationMapper.locationDtoToLocation(newEventDto.getLocation()))
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .publishedOn(null)
                .requestModeration(newEventDto.isRequestModeration())
                .state(EventState.PENDING)
                .title(newEventDto.getTitle())
                .views(views)
                .build();
    }

    public static EventState eventStateDtoToEventState(EventStateDto state) {
        if (state.name().equals(EventStateDto.PENDING.name())) {
            return EventState.PENDING;
        }
        if (state.name().equals(EventStateDto.CANCELED.name())) {
            return EventState.CANCELED;
        }
        if (state.name().equals(EventStateDto.PUBLISHED.name())) {
            return EventState.PUBLISHED;
        }
        throw new BadRequestException("Нет такого статуса" + state.name());
    }
}
