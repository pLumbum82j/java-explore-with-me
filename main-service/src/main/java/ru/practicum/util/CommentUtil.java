package ru.practicum.util;

import lombok.experimental.UtilityClass;
import ru.practicum.exceptions.ForbiddenEventException;
import ru.practicum.models.Comment;
import ru.practicum.models.Event;

@UtilityClass
public class CommentUtil {

    public void checkCommentOnEvent(Comment comment, Event event) {
        if (!comment.getEvent().getId().equals(event.getId())) {
            throw new ForbiddenEventException("Комментарий с id: " + comment.getId() + " не принадлежит событию с id: " + event.getId());
        }
    }
}
