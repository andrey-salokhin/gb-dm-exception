package org.example;

public class FormatException extends RuntimeException {

    public FormatException(String message) {
        super(message);
    }

    public FormatException(String message, Exception e) {
        super(message, e);
    }
}
