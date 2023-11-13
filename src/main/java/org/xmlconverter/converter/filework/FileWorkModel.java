package org.xmlconverter.converter.filework;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Setter
@Getter
public class FileWorkModel {
    private File xmlFile;
    private File csvFile;

    public void createCsvFile(@NonNull File selectedFile) throws Exception {
        selectedFile.createNewFile();
    }

    public void writeCsvData(@NonNull List<String[]> csvRecords) {
        try (val writer = new FileWriter(csvFile.getAbsolutePath(), StandardCharsets.UTF_8);
             val csvPrinter = new CSVPrinter(writer, CSVFormat.Builder.create().setDelimiter(";").build())) {
            csvPrinter.printRecord("рейс", "время", "направление", "статус", "тип", "авиакомпания");
            for (String[] record : csvRecords) {
                csvPrinter.printRecord((Object[]) record);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}