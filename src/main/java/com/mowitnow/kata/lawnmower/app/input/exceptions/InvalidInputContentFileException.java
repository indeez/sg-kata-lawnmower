package com.mowitnow.kata.lawnmower.app.input.exceptions;

public class InvalidInputContentFileException extends RuntimeException {

    public InvalidInputContentFileException() {
    }

    public InvalidInputContentFileException(String message) {
        super(message);
    }

    public InvalidInputContentFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputContentFileException(Throwable cause) {
        super(cause);
    }

    public InvalidInputContentFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
