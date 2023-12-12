package org.xmlconverter.bean;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.xmlconverter.converter.bean.FileWorkController;
import org.xmlconverter.converter.bean.FileWorkView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class FileWorkViewTest {

    @Test
    void testOpenXmlFile() throws Exception {
        val fileWorkControllerMock = mock(FileWorkController.class);
        val input = "1\n/path/to/xml/file\n";
        val in = new ByteArrayInputStream(input.getBytes());
        val scannerMock = new Scanner(in);
        val fileWorkView = new FileWorkView(fileWorkControllerMock, scannerMock);
        doNothing().when(fileWorkControllerMock).checkAndSetXmlFile(any());
        fileWorkView.openXmlFile();
        verify(fileWorkControllerMock).checkAndSetXmlFile(Paths.get("/path/to/xml/file"));
    }
    @Test
    void testSaveCsvFile() throws Exception {
        val fileWorkControllerMock = mock(FileWorkController.class);
        val input = "1\n/path/to/csv/file\n";
        val in = new ByteArrayInputStream(input.getBytes());
        val scannerMock = new Scanner(in);
        val fileWorkView = new FileWorkView(fileWorkControllerMock, scannerMock);
        doNothing().when(fileWorkControllerMock).createAndSetCsvFile(any());
        doNothing().when(fileWorkControllerMock).writeCsvDataToFile(any());
        fileWorkView.saveCsvFile(Collections.emptyList());
        verify(fileWorkControllerMock).createAndSetCsvFile(Paths.get("/path/to/csv/file"));
        verify(fileWorkControllerMock).writeCsvDataToFile(Collections.emptyList());
    }
}