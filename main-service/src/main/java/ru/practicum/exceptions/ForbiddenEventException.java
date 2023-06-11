package ru.practicum.exceptions;

public class ForbiddenEventException extends RuntimeException {
    public ForbiddenEventException(String message) {
        super(message);
    }
}
