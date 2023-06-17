package ru.practicum.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.exceptions.ResourceNotFoundException;
import ru.practicum.models.Comment;
import ru.practicum.models.Event;
import ru.practicum.models.enums.CommentState;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByEvent(Event event, Pageable pageable);

    List<Comment> findByEventAndStateIsNot(Event event, CommentState state, Pageable pageable);

    default Comment get(long id) {
        return findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Комментарий c id:  " + id + " не существует"));
    }
}