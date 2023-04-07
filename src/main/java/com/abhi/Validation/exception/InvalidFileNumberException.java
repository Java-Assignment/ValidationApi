package com.abhi.Validation.exception;

public class InvalidFileNumberException extends Throwable {
    public InvalidFileNumberException(String invalidFileNumberEntered) {
        super(invalidFileNumberEntered);
    }
}
