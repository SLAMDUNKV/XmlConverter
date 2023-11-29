package org.xmlconverter.converter.filework;

import lombok.val;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class FileWorkController {
    private final FileWorkModel fileWorkModel;
    private final Logger logger;

    public FileWorkController(FileWorkModel fileWorkModel) {
        this.fileWorkModel = fileWorkModel;
        this.logger = Logger.getLogger(FileWorkModel.class.getName());
    }

    public File getXmlFile() {
        return fileWorkModel.getXmlFile();
    }

    public void checkAndSetXmlFile(Path xmlFilePath) {
        val selectedXmlFile = xmlFilePath.toFile();
        if (selectedXmlFile.canRead()) {
            fileWorkModel.setXmlFile(selectedXmlFile);
            logger.info("Файл успешно открыт: " + selectedXmlFile.getAbsolutePath());
        } else {
            logger.severe("Невозможно прочитать выбранный файл: " + selectedXmlFile.getAbsolutePath());
            throw new RuntimeException();
        }
    }

    public void createAndSetCsvFile(Path csvFilePath) {
        val selectedCsvFile = new File( csvFilePath + "\\Airport.csv");
        try {
            fileWorkModel.createCsvFile(selectedCsvFile);
            fileWorkModel.setCsvFile(selectedCsvFile);
            logger.info("Файл успешно создан:" + selectedCsvFile.getAbsolutePath());
        } catch (Exception e) {
            logger.severe("Ошибка создания файла по выбранному пути:" + selectedCsvFile.getAbsolutePath());
            throw new RuntimeException(e);
        }
    }

    public void writeCsvDataToFile(List<String[]> csvRecords) {
        if (!csvRecords.isEmpty()) {
            try {
                fileWorkModel.writeCsvData(csvRecords);
                logger.info("Данные успешно записаны.");
            } catch (Exception e) {
                logger.severe("Ошибка при записи данных.");
                throw new RuntimeException(e);
            }
        } else {
            logger.info("Нет данных для сохранения.");
            throw new RuntimeException();
        }
    }
}
