package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.services.CommentsPublicService;

/**
 * Класс CommentsPublicController по энпоинту comments/event/{eventId}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comments/event/{eventId}")
public class CommentsPublicController {

    private final CommentsPublicService commentsPublicService;

}
