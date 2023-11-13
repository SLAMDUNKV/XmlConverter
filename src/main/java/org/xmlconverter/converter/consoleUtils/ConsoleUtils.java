package org.xmlconverter.converter.consoleUtils;

import lombok.val;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleUtils {
    private final Logger logger;
    private final Scanner console;

    public ConsoleUtils() {
        this.logger = Logger.getLogger(ConsoleUtils.class.getName());
        this.console = new Scanner(System.in);
    }

    // Метод для отображения CSV-данных
    public void showCsv(List<String[]> csvData) {
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

    public String readAirlineName() {
        logger.info("Введите название авиакомпании:");
        return console.nextLine();
    }
}