package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.models.dto.CommentDto;
import ru.practicum.models.dto.InputCommentDto;
import ru.practicum.services.CommentsPrivateService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Класс CommentsPrivateController по энпоинту private/comments
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/private/comments")
public class CommentsPrivateController {

    private final CommentsPrivateService commentsPrivateService;

    @GetMapping("/{commentId}/user/{userId}")
    CommentDto get(@PathVariable Long commentId,
                   @PathVariable Long userId) {
        return commentsPrivateService.get(commentId, userId);
    }

    @GetMapping("/event/{eventId}/user/{userId}")
    List<CommentDto> get(@PathVariable Long eventId,
                         @PathVariable Long userId,
                         @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                         @Positive @RequestParam(defaultValue = "10") Integer size) {
        return commentsPrivateService.get(eventId, userId, from, size);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto create(@Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsPrivateService.create(inputCommentDto);
    }

    @PatchMapping("/{commentId}")
    CommentDto update(@PathVariable Long commentId,
                      @Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsPrivateService.update(commentId, inputCommentDto);
    }

    @DeleteMapping("/{commentId}/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long commentId,
                @PathVariable Long userId) {
        commentsPrivateService.delete(commentId, userId);
    }
}