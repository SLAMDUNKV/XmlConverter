package org.xmlconverter.converter.conversion;

import lombok.val;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConversionView {
    private final ConversionController conversionController;
    private final Logger logger;

    public ConversionView(ConversionController conversionController) {
        this.conversionController = conversionController;
        this.logger = Logger.getLogger(ConversionView.class.getName());
    }

    // Метод для отображения CSV-данных
    public void showCsv() {
        val csvData = conversionController.getCsvRecords();
        if (csvData != null && !csvData.isEmpty()) {
            // Вывод CSV-данных в консоль
            val line = new StringBuilder();
            line.append("Преобразованные данные:");
            line.append("\nрейс;время;направление;статус;тип;авиакомпания");
            for (String[] record : csvData) {
                line.append("\n");
                for (int i = 0; i < record.length; i++) {
                    line.append(record[i]);
                    if (i < record.length - 1) {
                        line.append(";");
                    }
                }
            }
            logger.info(line.toString());
        } else {
            logger.info("Нет данных для отображения");
            throw new RuntimeException();
        }
    }




}