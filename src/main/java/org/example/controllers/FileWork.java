package org.example.controllers;

import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

public class FileWork {
    @Setter
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    @Setter
    private Logger logger = Logger.getLogger(FileWork.class.getName());

    public FileWork() {
        this.fileChooser.setAcceptAllFileFilterUsed(false);
    }

    // Метод для открытия XML-файла
    public File openXmlFile() {
        // Настройка диалога выбора файла
        fileChooser.setDialogTitle("Выберите XML-файл");
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files (*.xml)", "xml"));

        final int openDialog = fileChooser.showOpenDialog(null);
        if (openDialog == JFileChooser.APPROVE_OPTION) {
            File xmlFile = fileChooser.getSelectedFile();

            if (xmlFile.canRead()) {
                logger.info("XML файл успешно открыт:" + xmlFile.getAbsolutePath());
                return xmlFile;
            } else {
                logger.severe("Невозможно прочитать выбранный файл:" + xmlFile.getAbsolutePath());
                return null;
            }
        } else {
            logger.info("Выбор файла отменен.");
            return null;
        }
    }

    // Метод для отображения CSV-данных
    public List<String[]> showCsv(List<String[]> csvData) {
        if (csvData != null && !csvData.isEmpty()) {
            // Вывод CSV-данных в консоль
            System.out.println("Преобразованные данные:");
            System.out.println("\nрейс;время;направление;статус;тип;авиакомпания");

            for (String[] record : csvData) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < record.length; i++) {
                    line.append(record[i]);
                    if (i < record.length - 1) {
                        line.append(";");
                    }
                }
                System.out.println(line);
            }
            return csvData;
        } else {
            logger.info("Нет данных для отображения");
            return null;
        }
    }

    // Метод для записи CSV-файла
    public void writeCsvFile(List<String[]> csvData) {
        if (csvData != null && !csvData.isEmpty()) {
            // Настройка диалога выбора каталога для сохранения файла
            fileChooser.setDialogTitle("Выберите каталог для сохранения CSV-файла");
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files (*.csv)", "csv"));

            final int openDialog = fileChooser.showSaveDialog(null);
            if (openDialog == JFileChooser.APPROVE_OPTION) {
                File csvFile = fileChooser.getSelectedFile();

                //Запись данных в формате csv
                try (FileWriter writer = new FileWriter(csvFile.getAbsolutePath() + ".csv", StandardCharsets.UTF_8);
                     CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'))) {
                    csvPrinter.printRecord("рейс", "время", "направление", "статус", "тип", "авиакомпания");
                    for (String[] record : csvData) {
                        csvPrinter.printRecord((Object[]) record);
                    }
                    logger.info("Файл успешно сохранён по пути:" + csvFile.getAbsolutePath() + ".csv");

                } catch (IOException e) {
                    logger.severe("Ошибка при сохранении файла:" + e.getMessage());
                }
            } else
                logger.info("Выбор каталога для сохранения отменён");

        } else
            logger.info("Нет данных для сохранения");
    }
}