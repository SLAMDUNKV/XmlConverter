package org.xmlconverter.converter.bean;

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

    public boolean createCsvFile(@NonNull File selectedFile) throws IOException {
        return selectedFile.createNewFile();
    }

    public boolean writeCsvData(@NonNull List<String[]> csvRecords) throws IOException {
        val writer = new FileWriter(csvFile.getAbsolutePath(), StandardCharsets.UTF_8);
        val csvPrinter = new CSVPrinter(writer, CSVFormat.Builder.create().setDelimiter(";").build());
        csvPrinter.printRecord("рейс", "время", "направление", "статус", "тип", "авиакомпания");
        for (String[] record : csvRecords) {
            csvPrinter.printRecord((Object[]) record);
        }
        writer.close();
        csvPrinter.close();
        return true;
    }
}