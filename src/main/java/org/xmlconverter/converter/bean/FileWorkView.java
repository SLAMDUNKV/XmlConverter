package org.xmlconverter.converter.bean;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class FileWorkView {
    private final FileWorkController fileWorkController;
    private final Scanner scanner;

    public FileWorkView(FileWorkController fileWorkController, Scanner scanner) {
        this.fileWorkController = fileWorkController;
        this.scanner = scanner;
    }

    public void openXmlFile() throws Exception {
        log.info("Выберите режим открытия XML-файла:\n1. Интерактивный режим\n2. Автоматический режим");
        val choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                log.info("Введите путь к XML-файлу:");
                val xmlFilePath = Paths.get(scanner.nextLine());
                fileWorkController.checkAndSetXmlFile(xmlFilePath);
            }
            case 2 -> {
                val xmlFilePath = Paths.get(System.getProperty("user.home"), "Documents", "Airport.xml");
                fileWorkController.checkAndSetXmlFile(xmlFilePath);
            }
            default -> throw new IncorrectChoiceException("Неверный выбор режима.");
        }
    }

    public void saveCsvFile(List<String[]> csvRecords) throws Exception {
        log.info("Выберите режим сохранения CSV-файла:\n1. Интерактивный режим\n2. Автоматический режим");
        val choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                log.info("Введите путь для сохранения CSV-файла:");
                val csvFilePath = Paths.get(scanner.nextLine());
                fileWorkController.createAndSetCsvFile(csvFilePath);
                fileWorkController.writeCsvDataToFile(csvRecords);
            }
            case 2 -> {
                val csvFilePath = Paths.get(System.getProperty("user.home"), "Documents");
                fileWorkController.createAndSetCsvFile(csvFilePath);
                fileWorkController.writeCsvDataToFile(csvRecords);
            }
            default -> throw new IncorrectChoiceException("Неверный выбор режима.");
        }

    }
}
