package org.xmlconverter.converter.bean;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class FileWorkController {
    private final FileWorkModel fileWorkModel;

    public FileWorkController(FileWorkModel fileWorkModel) {
        this.fileWorkModel = fileWorkModel;
    }

    public File getXmlFile() {
        return fileWorkModel.getXmlFile();
    }

    public void checkAndSetXmlFile(@NonNull Path xmlFilePath) throws UnreadableFileException {
        val selectedXmlFile = xmlFilePath.toFile();
        if (selectedXmlFile.canRead()) {
            fileWorkModel.setXmlFile(selectedXmlFile);
            log.info("Файл успешно открыт: " + selectedXmlFile.getAbsolutePath());
        } else {
            throw new UnreadableFileException("Невозможно прочитать выбранный файл: " + selectedXmlFile.getAbsolutePath());
        }
    }

    public void createAndSetCsvFile(Path csvFilePath) throws FileCreationException {
        val selectedCsvFile = new File(csvFilePath + "\\Airport.csv");
        try {
            fileWorkModel.createCsvFile(selectedCsvFile);
            fileWorkModel.setCsvFile(selectedCsvFile);
            log.info("Файл успешно создан:" + selectedCsvFile.getAbsolutePath());
        } catch (IOException ioException) {
            throw new FileCreationException("Ошибка создания файла по выбранному пути: " + selectedCsvFile.getAbsolutePath(),
                    ioException);
        }
    }

    public void writeCsvDataToFile(@NonNull List<String[]> csvRecords) throws DataWritingException {
        if (!csvRecords.isEmpty()) {
            try {
                fileWorkModel.writeCsvData(csvRecords);
                log.info("Данные успешно записаны.");
            } catch (IOException ioException) {
                throw new DataWritingException("Ошибка при записи данных.", ioException);
            }
        } else {
            throw new DataWritingException("Нет данных для сохранения.");
        }
    }
}
