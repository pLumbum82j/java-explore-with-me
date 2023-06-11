package ru.practicum.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.models.User;
import ru.practicum.models.dto.NewUserRequest;
import ru.practicum.models.dto.UserDto;
import ru.practicum.models.dto.UserShortDto;

@UtilityClass
public class UserMapper {
    public UserDto userToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public UserShortDto userToUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public User newUserRequestToUser(NewUserRequest newUserRequest) {
        return User.builder()
                .name(newUserRequest.getName())
                .email(newUserRequest.getEmail())
                .build();
    }
}
