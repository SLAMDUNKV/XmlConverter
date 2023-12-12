package org.xmlconverter.converter.bean;

public class DataWritingException extends Exception {
    public DataWritingException() {}

    public DataWritingException(String message) {
        super(message);
    }

    public DataWritingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataWritingException(Throwable cause) {
        super(cause);
    }

    public DataWritingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
