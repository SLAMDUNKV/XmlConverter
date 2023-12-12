package org.xmlconverter.converter.service;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class ConversionController {
    private final ConversionModel conversionModel;
    private final Scanner scanner;
    
    public ConversionController(ConversionModel conversionModel, Scanner scanner) {
        this.conversionModel = conversionModel;
        this.scanner = scanner;
    }

    public void starConversion(File xmlFile) throws ParsingXmlException {
        try {
            conversionModel.convertFlightListToCsvList(conversionModel.convertXmlToFlightList(xmlFile));
            log.info("Парсинг XML-файла прошёл успешно!");
        } catch (Exception exception ) {
            throw new ParsingXmlException("Ошибка при парсинге XML-фала.", exception);
        }
    }

    public List<String[]> getCsvRecords() {
        return conversionModel.getRecords();
    }

    public void readAirlineName() {
        log.info("Введите название авиакомпании:");
        conversionModel.setAirlineName(scanner.nextLine());
    }

}
