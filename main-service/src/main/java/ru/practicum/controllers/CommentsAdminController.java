package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.services.CommentsAdminService;

/**
 * Класс CommentsAdminController по энпоинту admin/comments
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class CommentsAdminController {

    private final CommentsAdminService commentsAdminService;

}
