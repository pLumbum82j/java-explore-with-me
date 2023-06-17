package ru.practicum.services;

import ru.practicum.models.dto.CommentDto;
import ru.practicum.models.dto.InputCommentDto;

import java.util.List;

public interface CommentsPrivateService {

    CommentDto get(Long commentId, Long userId);

    List<CommentDto> get(Long eventId, Long userId, Integer from, Integer size);

    CommentDto create(InputCommentDto inputCommentDto);

    CommentDto update(Long commentId, InputCommentDto inputCommentDto);

    void delete(Long commentId, Long userId);
}