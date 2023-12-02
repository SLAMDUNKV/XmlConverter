package org.xmlconverter.filework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.xmlconverter.converter.filework.FileWorkModel;
import static org.junit.jupiter.api.Assertions.*;

class FileWorkModelTest {

    private FileWorkModel fileWorkModel;

    @BeforeEach
    void setUp() {
        fileWorkModel = new FileWorkModel();
    }

    @Test
    void testCreateCsvFile() {
        try {
            File tempFile = File.createTempFile("temp", ".csv");
            fileWorkModel.createCsvFile(tempFile);
            assertTrue(tempFile.exists());
        } catch (Exception e) {
            fail("Exception not expected", e);
        }
    }

    @Test
    void testWriteCsvData() {
        try {
            File tempCsvFile = File.createTempFile("temp", ".csv");
            fileWorkModel.setCsvFile(tempCsvFile);

            List<String[]> csvRecords = Arrays.asList(
                    new String[]{"1", "12:00", "Direction1", "Status1", "Type1", "Airline1"},
                    new String[]{"2", "14:00", "Direction2", "Status2", "Type2", "Airline2"}
            );

            assertTrue(fileWorkModel.writeCsvData(csvRecords));

            // Now you can add assertions to check the content of the file if needed
            // For example, you can use a CSV parsing library to read the content and assert specific values
        } catch (IOException e) {
            fail("Exception not expected", e);
        }
    }
}
