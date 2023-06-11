package ru.practicum.exceptions;

public class ConflictNameAndEmailException extends RuntimeException {
    public ConflictNameAndEmailException(String message) {
        super(message);
    }
}
