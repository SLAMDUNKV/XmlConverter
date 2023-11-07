package org.example;

import org.example.controllers.Conversion;
import org.example.controllers.FileWork;

import static org.example.view.ConsoleUtils.readAirlineName;

public class Main {
    public static void main(String[] args) {
        convertXmlToCsv();
    }

    public static void convertXmlToCsv() {
        FileWork fileWork = new FileWork();

        fileWork.writeCsvFile(
                fileWork.showCsv(
                        Conversion.convertFlightListToCsvList(
                                readAirlineName(), Conversion.convertXmlToFlightList(
                                        fileWork.openXmlFile()))));
    }
}
