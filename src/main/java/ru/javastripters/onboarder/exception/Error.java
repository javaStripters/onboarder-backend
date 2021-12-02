package ru.javastripters.onboarder.exception;

import org.springframework.http.HttpStatus;

public record Error(HttpStatus httpStatus, String message) {}
