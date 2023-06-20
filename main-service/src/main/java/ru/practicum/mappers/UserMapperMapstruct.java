package ru.practicum.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import ru.practicum.models.User;
import ru.practicum.models.dto.NewUserRequest;
import ru.practicum.models.dto.UserDto;
import ru.practicum.models.dto.UserShortDto;

/**
 * Утилитарный класс UserMapper для преобразования User / UserDto / UserShortDto / NewUserRequest
 */
@Service
@Mapper(componentModel = "spring")
public interface UserMapperMapstruct {

    /**
     * Преобразование User в UserDto
     *
     * @param user Объект User
     * @return Преобразованный объект UserDto
     */
    UserDto userToDto(User user);

    /**
     * Преобразование User в UserShortDto
     *
     * @param user Объект User
     * @return Преобразованный объект UserShortDto
     */
    UserShortDto userToUserShortDto(User user);

    /**
     * Преобразование NewUserRequest в User
     *
     * @param newUserRequest Объект NewUserRequest
     * @return Преобразованный объект User
     */
    User newUserRequestToUser(NewUserRequest newUserRequest);
}
