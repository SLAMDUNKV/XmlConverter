package org.xmlconverter.converter.bean;

public class FileCreationException extends Exception {
    public FileCreationException() {}

    public FileCreationException(String message) {
        super(message);
    }

    public FileCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCreationException(Throwable cause) {
        super(cause);
    }

    public FileCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
