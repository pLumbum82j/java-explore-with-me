package ru.practicum.exceptions;

public class ValidationDateException extends RuntimeException {
    public ValidationDateException(String message) {
        super(message);
    }
}
