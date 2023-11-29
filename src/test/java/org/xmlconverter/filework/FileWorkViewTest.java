package org.xmlconverter.filework;

import org.junit.jupiter.api.Test;
import org.xmlconverter.converter.filework.FileWorkController;
import org.xmlconverter.converter.filework.FileWorkView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class FileWorkViewTest {

    @Test
    void testOpenXmlFile() {
        // Mock FileWorkController
        FileWorkController fileWorkControllerMock = mock(FileWorkController.class);

        // Mock Scanner
        String input = "1\n/path/to/xml/file\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scannerMock = new Scanner(in);

        // Create FileWorkView with mocks
        FileWorkView fileWorkView = new FileWorkView(fileWorkControllerMock, scannerMock);

        // Mock behavior of fileWorkControllerMock
        doNothing().when(fileWorkControllerMock).checkAndSetXmlFile(any());

        // Perform the method to be tested
        fileWorkView.openXmlFile();

        // Verify that the correct methods were called on the mocks
        verify(fileWorkControllerMock).checkAndSetXmlFile(Paths.get("/path/to/xml/file"));
    }

    @Test
    void testSaveCsvFile() {
        // Mock FileWorkController
        FileWorkController fileWorkControllerMock = mock(FileWorkController.class);

        // Mock Scanner
        String input = "1\n/path/to/csv/file\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scannerMock = new Scanner(in);

        // Create FileWorkView with mocks
        FileWorkView fileWorkView = new FileWorkView(fileWorkControllerMock, scannerMock);

        // Mock behavior of fileWorkControllerMock
        doNothing().when(fileWorkControllerMock).createAndSetCsvFile(any());
        doNothing().when(fileWorkControllerMock).writeCsvDataToFile(any());

        // Perform the method to be tested
        fileWorkView.saveCsvFile(Collections.emptyList());

        // Verify that the correct methods were called on the mocks
        verify(fileWorkControllerMock).createAndSetCsvFile(Paths.get("/path/to/csv/file"));
        verify(fileWorkControllerMock).writeCsvDataToFile(Collections.emptyList());
    }
}