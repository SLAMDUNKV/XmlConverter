package org.xmlconverter.converter.bean;

public class UnreadableFileException extends Exception {
    public UnreadableFileException() {}

    public UnreadableFileException(String message) {
        super(message);
    }

    public UnreadableFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnreadableFileException(Throwable cause) {
        super(cause);
    }

    public UnreadableFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
