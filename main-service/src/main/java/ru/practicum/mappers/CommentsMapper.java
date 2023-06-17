package ru.practicum.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.exceptions.BadRequestException;
import ru.practicum.models.Comment;
import ru.practicum.models.Event;
import ru.practicum.models.User;
import ru.practicum.models.dto.CommentDto;
import ru.practicum.models.dto.CommentStateDto;
import ru.practicum.models.dto.InputCommentDto;
import ru.practicum.models.enums.CommentState;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class CommentsMapper {


    public Comment createComment(InputCommentDto inputCommentDto, User user, Event event) {
        return Comment.builder()
                .text(inputCommentDto.getText())
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .author(user)
                .event(event)
                .state(CommentState.PUBLISHED)
                .build();
    }

    public CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .createdOn(comment.getCreatedOn())
                .author(UserMapper.userToUserShortDto(comment.getAuthor()))
                .event(EventMapper.eventToEventCommentDto(comment.getEvent()))
                .state(comment.getState().name())
                .build();

    }

    public CommentState toCommentState(CommentStateDto commentStateDto) {
        if (commentStateDto.name().equals(CommentState.UPDATE.name())) {
            return CommentState.UPDATE;
        }
        if (commentStateDto.name().equals(CommentState.PUBLISHED.name())) {
            return CommentState.PUBLISHED;
        }
        if (commentStateDto.name().equals(CommentState.CANCELED.name())) {
            return CommentState.CANCELED;
        } else {
            throw new BadRequestException("Статус не соответствует модификатору доступа");
        }
    }
}
