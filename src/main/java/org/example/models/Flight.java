package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Flight {
    private final String id;
    private final String time;
    private final String destination;
    private final String status;
    private final String type;
    private final String company;
}
