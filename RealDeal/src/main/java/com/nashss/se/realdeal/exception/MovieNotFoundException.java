package com.nashss.se.realdeal.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(Throwable cause) {
        super(cause);
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
