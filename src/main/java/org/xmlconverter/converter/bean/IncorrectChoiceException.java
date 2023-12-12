package org.xmlconverter.converter.bean;

public class IncorrectChoiceException extends Exception {
    public IncorrectChoiceException() {
    }

    public IncorrectChoiceException(String message) {
        super(message);
    }

    public IncorrectChoiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectChoiceException(Throwable cause) {
        super(cause);
    }

    public IncorrectChoiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
