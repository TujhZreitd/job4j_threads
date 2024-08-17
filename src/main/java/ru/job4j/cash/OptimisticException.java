package ru.job4j.cash;

public class OptimisticException extends IllegalArgumentException {
    public OptimisticException(String message) {
        super(message);
    }
}
