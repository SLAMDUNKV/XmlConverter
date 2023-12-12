package org.xmlconverter.converter;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.xmlconverter.converter.service.ConversionController;
import org.xmlconverter.converter.service.ConversionModel;
import org.xmlconverter.converter.service.ConversionView;
import org.xmlconverter.converter.bean.FileWorkController;
import org.xmlconverter.converter.bean.FileWorkModel;
import org.xmlconverter.converter.bean.FileWorkView;

import java.util.Scanner;

@Slf4j
public class Converter {

    public void convertXmlToCsv() {
        try (val scanner = new Scanner(System.in)) {
            val fileWorkModel = new FileWorkModel();
            val fileWorkController = new FileWorkController(fileWorkModel);
            val fileWorkView = new FileWorkView(fileWorkController, scanner);
            val conversionModel = new ConversionModel();
            val conversionController = new ConversionController(conversionModel, scanner);
            val conversionView = new ConversionView(conversionController);
            fileWorkView.openXmlFile();
            conversionController.readAirlineName();
            conversionController.starConversion(fileWorkController.getXmlFile());
            conversionView.displayCsvData();
            fileWorkView.saveCsvFile(conversionController.getCsvRecords());
        } catch (Exception exception) {
            log.error("Произошла ошибка во время работы программы.", exception);
        }
    }
}
