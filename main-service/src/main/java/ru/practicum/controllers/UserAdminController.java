package ru.practicum.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.models.dto.NewUserRequest;
import ru.practicum.models.dto.UserDto;
import ru.practicum.services.UserAdminService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserAdminController {
    private final UserAdminService userAdminService;

    @GetMapping
    List<UserDto> get(@RequestParam(required = false) List<Long> ids,
                      @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                      @Positive @RequestParam(defaultValue = "10") Integer size) {
        return userAdminService.get(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto create(@Validated @RequestBody NewUserRequest newUserRequest) {
        return userAdminService.create(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long userId) {
        userAdminService.delete(userId);
    }
}
