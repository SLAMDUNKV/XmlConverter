package org.xmlconverter.converter.service;

public class DisplayCsvDataException extends Exception {
    public DisplayCsvDataException() {}

    public DisplayCsvDataException(String message) {
        super(message);
    }

    public DisplayCsvDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisplayCsvDataException(Throwable cause) {
        super(cause);
    }

    public DisplayCsvDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
