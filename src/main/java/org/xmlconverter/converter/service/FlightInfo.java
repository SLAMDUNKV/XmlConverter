package org.xmlconverter.converter.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class FlightInfo {
    private final String id;
    private final String time;
    private final String destination;
    private final String status;
    private final String type;
    private final String company;
}
