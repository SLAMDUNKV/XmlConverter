package org.xmlconverter.converter.service;

public class ParsingXmlException extends Exception {
    public ParsingXmlException() {}

    public ParsingXmlException(String message) {
        super(message);
    }

    public ParsingXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingXmlException(Throwable cause) {
        super(cause);
    }

    public ParsingXmlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
