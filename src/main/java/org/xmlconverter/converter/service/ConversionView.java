package org.xmlconverter.converter.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class ConversionView {
    private final ConversionController conversionController;
    public ConversionView(ConversionController conversionController) {
        this.conversionController = conversionController;
    }

    // Метод для отображения CSV-данных
    public void displayCsvData() throws DisplayCsvDataException {
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
            log.info(line.toString());
        } else {
            throw new DisplayCsvDataException("Нет данных для отображения");
        }
    }


}