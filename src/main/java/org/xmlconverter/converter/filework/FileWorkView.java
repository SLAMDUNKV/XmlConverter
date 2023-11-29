package org.xmlconverter.converter.filework;

import lombok.val;

import javax.swing.filechooser.FileSystemView;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileWorkView {
    private final FileWorkController fileWorkController;
    private final Logger logger;
    private final Scanner scanner;

    public FileWorkView(FileWorkController fileWorkController, Scanner scanner) {
        this.fileWorkController = fileWorkController;
        this.logger = Logger.getLogger(FileSystemView.class.getName());
        this.scanner = scanner;
    }

    public void openXmlFile() {
        logger.info("Выберите режим открытия XML-файла:\n1. Интерактивный режим\n2. Автоматический режим");
        val choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                logger.info("Введите путь к XML-файлу:");
                val xmlFilePath = Paths.get(scanner.nextLine());
                fileWorkController.checkAndSetXmlFile(xmlFilePath);
            }
            case 2 -> {
                val xmlFilePath = Paths.get(System.getProperty("user.home"), "Documents", "Airport.xml");
                fileWorkController.checkAndSetXmlFile(xmlFilePath);
            }
            default -> {
                logger.info("Неверный выбор режима.");
                throw new RuntimeException();
            }
        }
    }

    public void saveCsvFile(List<String[]> csvRecords) {
        logger.info("Выберите режим сохранения CSV-файла:\n1. Интерактивный режим\n2. Автоматический режим");
        val choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                logger.info("Введите путь для сохранения CSV-файла:");
                val csvFilePath = Paths.get(scanner.nextLine());
                fileWorkController.createAndSetCsvFile(csvFilePath);
                fileWorkController.writeCsvDataToFile(csvRecords);
            }
            case 2 -> {
                val csvFilePath = Paths.get(System.getProperty("user.home"), "Documents");
                fileWorkController.createAndSetCsvFile(csvFilePath);
                fileWorkController.writeCsvDataToFile(csvRecords);
            }
            default -> {
                logger.info("Неверный выбор режима.");
                throw new RuntimeException();
            }
        }

    }
}
