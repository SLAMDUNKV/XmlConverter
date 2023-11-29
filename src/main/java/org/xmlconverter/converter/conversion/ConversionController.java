package org.xmlconverter.converter.conversion;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class ConversionController {
    private final ConversionModel conversionModel;
    private final Logger logger;
    private final Scanner scanner;


    public ConversionController(ConversionModel conversionModel, Scanner scanner) {
        this.conversionModel = conversionModel;
        this.logger = Logger.getLogger(ConversionController.class.getName());
        this.scanner = new Scanner(System.in);
    }

    public void starConversion(File xmlFile) {
        try {
            conversionModel.convertFlightListToCsvList(conversionModel.convertXmlToFlightList(xmlFile));
            logger.info("Парсинг XML файла прошёл успешно!");
        } catch (Exception e) {
            logger.severe("Ошибка при парсинге XML файла!");
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getCsvRecords() {
        return conversionModel.getRecords();
    }

    public void readAirlineName() {
        logger.info("Введите название авиакомпании:");
        conversionModel.setAirlineName(scanner.nextLine());
    }

}
