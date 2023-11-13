package org.xmlconverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.xmlconverter.converter.filework.FileWorkController;
import org.xmlconverter.converter.filework.FileWorkModel;
import org.xmlconverter.converter.filework.FileWorkView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FileWorkTest {
    private FileWorkView fileWorkView;
    private FileWorkController fileWorkController;
    private FileWorkModel fileWorkModel;
    private JFileChooser mockFileChooser;
    private Logger mockLogger;

    @BeforeEach
    void setUp() {
        this.mockFileChooser = Mockito.mock(JFileChooser.class);
        this.mockLogger = Mockito.mock(Logger.class);

        this.fileWorkModel = new FileWorkModel();
        this.fileWorkController = new FileWorkController(fileWorkModel, mockLogger);
        this.fileWorkView = new FileWorkView(fileWorkController, mockFileChooser);
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
            fileWorkView.openXmlFile();

            // Проверяем, что возвращенный файл совпадает с validXmlFile
            assertEquals(validXmlFile, fileWorkController.getXmlFile());
            Mockito.verify(mockLogger).info(Mockito.contains("Файл успешно открыт:"));
        }

        @Test
        void testOpenXmlFileWithInvalidFile() {
            // Задаем поведение макета для методов showOpenDialog и getSelectedFile
            when(mockFileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
            when(mockFileChooser.getSelectedFile()).thenReturn(new File("No_file.xml"));

            // Проверяем, что результат равен null для несуществующего файла
            assertThrows(RuntimeException.class, () -> {
                // Вызываем метод openXmlFile
                fileWorkView.openXmlFile();
            });
            Mockito.verify(mockLogger).severe(Mockito.contains("Невозможно прочитать выбранный файл:"));
        }

        @Test
        void testOpenXmlFileCanceled() {
            // Задаем поведение макета для метода showOpenDialog при отмене выбора файла
            when(mockFileChooser.showOpenDialog(null)).thenReturn(JFileChooser.CANCEL_OPTION);

            assertThrows(RuntimeException.class, () -> {
                // Вызываем метод openXmlFile
                fileWorkView.openXmlFile();
            });

            Mockito.verify(mockLogger).info("Выбор файла отменен.");
        }
    }

    @Nested
    class WriteFileTest {
        @Test
        void testWriteCsvFileWithValidData() throws IOException {
            List<String[]> csvData = new ArrayList<>();
            csvData.add(new String[]{"рейс1", "время1", "направление1", "статус1", "тип1", "авиакомпания1"});

            // Задаем поведение макета fileChooser
            when(mockFileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
            when(mockFileChooser.getSelectedFile()).thenReturn(new File("test"));

            // Вызываем метод writeCsvFile
            fileWorkView.saveCsvFile(csvData);

            // Получаем содержимое записанного файла
            String fileContent = Files.readString(new File("test.csv").toPath());

            // Ожидаемое содержимое файла
            String expectedContent =
                    "рейс;время;направление;статус;тип;авиакомпания\r\nрейс1;время1;направление1;статус1;тип1;авиакомпания1\r\n";

            // Проверяем, что содержимое файла соответствует ожиданиям
            assertEquals(expectedContent, fileContent);

            // Проверяем, что логгер записал сообщение об успешном сохранении
            Mockito.verify(mockLogger).info(Mockito.contains("Файл успешно создан:"));
        }

        @Test
        void testWriteCsvFileWithNoData() {
            List<String[]> csvData = new ArrayList<>();

            // Задаём поведение макета fileChooser
            when(mockFileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
            when(mockFileChooser.getSelectedFile()).thenReturn(new File("test"));

            // Вызываем метод writeCsvFile без данных
            assertThrows(RuntimeException.class, () -> {
                // Вызываем метод openXmlFile
                fileWorkView.saveCsvFile(csvData);
            });
            // Проверяем, что логгер записал информационное сообщение
            Mockito.verify(mockLogger).info("Нет данных для сохранения.");
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
