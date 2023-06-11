package ru.practicum.exceptions;

public class ConflictDeleteException extends RuntimeException {
    public ConflictDeleteException(String message) {
        super(message);
    }
}
