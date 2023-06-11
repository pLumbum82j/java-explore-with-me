package ru.practicum.services;


import ru.practicum.models.dto.NewUserRequest;
import ru.practicum.models.dto.UserDto;

import java.util.List;

public interface UserAdminService {
    List<UserDto> get(List<Long> ids, int from, int size);

    UserDto create(NewUserRequest newUserRequest);

    void delete(Long id);
}
