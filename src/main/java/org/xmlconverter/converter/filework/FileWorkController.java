package org.xmlconverter.converter.filework;

import lombok.val;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class FileWorkController {
    private final FileWorkModel fileWorkModel;
    private final Logger logger;

    public FileWorkController(FileWorkModel fileWorkModel) {
        this.fileWorkModel = fileWorkModel;
        this.logger = Logger.getLogger(FileWorkModel.class.getName());
    }

    public FileWorkController(FileWorkModel fileWorkModel, Logger logger) {
        this.fileWorkModel = fileWorkModel;
        this.logger = logger;
    }

    public File getXmlFile() {
        return fileWorkModel.getXmlFile();
    }

    public void checkAndSetXmlFile(JFileChooser fileChooser, int openDialog) {
        if (openDialog == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.canRead()) {
                fileWorkModel.setXmlFile(selectedFile);
                logger.info("Файл успешно открыт: " + selectedFile.getAbsolutePath());
            } else {
                logger.severe("Невозможно прочитать выбранный файл: " + selectedFile.getAbsolutePath());
                throw new RuntimeException();
            }
        } else {
            logger.info("Выбор файла отменен.");
            throw new RuntimeException();
        }
    }

    public void createAndSetCsvFile(int saveDialog, JFileChooser fileChooser) {
        if (saveDialog == JFileChooser.APPROVE_OPTION) {
            val selectedCsvFile = new File(fileChooser.getSelectedFile().toString() + ".csv");
            try {
                fileWorkModel.createCsvFile(selectedCsvFile);
                fileWorkModel.setCsvFile(selectedCsvFile);
                logger.info("Файл успешно создан:" + selectedCsvFile.getAbsolutePath());
            } catch (Exception e) {
                logger.severe("Ошибка создания файла по выбранному пути:" + selectedCsvFile.getAbsolutePath());
                throw new RuntimeException(e);
            }
        } else {
            logger.info("Выбор директории отменен.");
            throw new RuntimeException();
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
