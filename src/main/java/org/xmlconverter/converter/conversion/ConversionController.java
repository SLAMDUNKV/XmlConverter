package org.xmlconverter.converter.conversion;

import org.xmlconverter.converter.consoleUtils.ConsoleUtils;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;


public class ConversionController {
    private final ConversionModel conversionModel;
    private final ConsoleUtils consoleUtils;
    private final Logger logger;

    public ConversionController(ConversionModel conversionModel, ConsoleUtils consoleUtils) {
        this.conversionModel = conversionModel;
        this.consoleUtils = consoleUtils;
        this.logger = Logger.getLogger(ConversionController.class.getName());
    }

    public void starConversion(File xmlFile) {
        try {
            conversionModel.convertFlightListToCsvList(consoleUtils.readAirlineName(),
                    conversionModel.convertXmlToFlightList(xmlFile));
            logger.info("Парсинг XML файла прошёл успешно!");
        } catch (Exception e) {
            logger.severe("Ошибка при парсинге XML файла!");
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getCsvRecords() {
        return conversionModel.getRecords();
    }

}
