package ru.practicum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.models.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

}
