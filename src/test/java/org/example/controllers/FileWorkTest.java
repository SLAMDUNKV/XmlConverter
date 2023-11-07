package org.example.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class FileWorkTest {
    private FileWork fileWork;
    private JFileChooser mockFileChooser;
    private Logger mockLogger;

    @BeforeEach
    void setUp() {
        this.fileWork = new FileWork();

        // Установка макетов JFileChooser и Logger
        this.mockFileChooser = Mockito.mock(JFileChooser.class);
        this.mockLogger = Mockito.mock(Logger.class);
        this.fileWork.setFileChooser(mockFileChooser);
        this.fileWork.setLogger(mockLogger);
    }

    @Nested
    class OpenXmlFileTest {
        @Test
        void testOpenXmlFileWithValidFile() {
            File validXmlFile = new File("src/test/resources/test-data/valid_test_data.xml");

            // Задаем поведение макета для методов showOpenDialog и getSelectedFile
            when(mockFileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
            when(mockFileChooser.getSelectedFile()).thenReturn(validXmlFile);

            // Вызываем метод openXmlFile
            File result = fileWork.openXmlFile();

            // Проверяем, что возвращенный файл совпадает с validXmlFile
            assertEquals(validXmlFile, result);
            Mockito.verify(mockLogger).info(Mockito.contains("XML файл успешно открыт:"));
        }

        @Test
        void testOpenXmlFileWithInvalidFile() {
            // Задаем поведение макета для методов showOpenDialog и getSelectedFile
            when(mockFileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
            when(mockFileChooser.getSelectedFile()).thenReturn(new File("No_file.xml"));

            // Вызываем метод openXmlFile
            File result = fileWork.openXmlFile();

            // Проверяем, что результат равен null для несуществующего файла
            assertNull(result);
            Mockito.verify(mockLogger).severe(Mockito.contains("Невозможно прочитать выбранный файл:"));
        }

        @Test
        void testOpenXmlFileCanceled() {
            // Задаем поведение макета для метода showOpenDialog при отмене выбора файла
            when(mockFileChooser.showOpenDialog(null)).thenReturn(JFileChooser.CANCEL_OPTION);

            // Вызываем метод openXmlFile
            File result = fileWork.openXmlFile();

            // Проверяем, что результат равен null из-за отмены выбора файла
            assertNull(result);
            Mockito.verify(mockLogger).info("Выбор файла отменен.");
        }
    }

    @Nested
    class WriteFileTest {
        @Test
        void testWriteCsvFileWithValidData() throws IOException {
            List<String[]> csvData = new ArrayList<>();
            csvData.add(new String[]{"рейс1", "время1", "направление1", "статус1", "тип1", "авиакомпания1"});

            // // Задаем поведение макета fileChooser
            when(mockFileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
            when(mockFileChooser.getSelectedFile()).thenReturn(new File("test"));

            // Вызываем метод writeCsvFile
            fileWork.writeCsvFile(csvData);

            // Получаем содержимое записанного файла
            String fileContent = Files.readString(new File("test.csv").toPath());

            // Ожидаемое содержимое файла
            String expectedContent =
                    "рейс;время;направление;статус;тип;авиакомпания\r\nрейс1;время1;направление1;статус1;тип1;авиакомпания1\r\n";

            // Проверяем, что содержимое файла соответствует ожиданиям
            assertEquals(expectedContent, fileContent);

            // Проверяем, что логгер записал сообщение об успешном сохранении
            Mockito.verify(mockLogger).info(Mockito.contains("Файл успешно сохранён по пути:"));
        }

        @Test
        void testWriteCsvFileWithNoData() {
            List<String[]> csvData = new ArrayList<>();

            // Задаём поведение макета fileChooser
            when(mockFileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
            when(mockFileChooser.getSelectedFile()).thenReturn(new File("test"));

            // Вызываем метод writeCsvFile без данных
            fileWork.writeCsvFile(csvData);

            // Проверяем, что логгер записал информационное сообщение
            Mockito.verify(mockLogger).info("Нет данных для сохранения");
        }

        @AfterEach
        void cleanup() {
            // Удаление файла "test.csv" после каждого теста
            File testCsvFile = new File("test.csv");
            if (testCsvFile.exists()) {
                testCsvFile.delete();
            }
        }
    }
}
