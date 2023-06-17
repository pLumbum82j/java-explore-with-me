package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.services.CommentsPrivateService;

/**
 * Класс CommentsPrivateController по энпоинту private/comments
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/private/comments")
public class CommentsPrivateController {

    private final CommentsPrivateService commentsPrivateService;

}
