package org.xmlconverter.converter.filework;

import lombok.val;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.util.List;

public class FileWorkView {
    private final FileWorkController fileWorkController;
    private final JFileChooser fileChooser;

    public FileWorkView(FileWorkController fileWorkController) {
        this.fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        this.fileWorkController = fileWorkController;
    }

    // Конструктор для тестовых методов
    public FileWorkView(FileWorkController fileWorkController, JFileChooser testFileChooser) {
        this.fileChooser = testFileChooser;
        this.fileWorkController = fileWorkController;
    }

    public void openXmlFile() {
        // Настройка и отображение диалога выбора файла
        fileChooser.setDialogTitle("Выберите файл");
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files (*.xml)", "xml"));
        val openDialog = fileChooser.showOpenDialog(null);

        fileWorkController.checkAndSetXmlFile(fileChooser, openDialog);
    }

    public void saveCsvFile(List<String[]> csvRecords) {
        // Настройка и отображение диалога выбора директории для сохранения
        fileChooser.setDialogTitle("Выберите каталог для сохранения CSV-файла");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV files (*.csv)", "csv"));
        val saveDialog = fileChooser.showSaveDialog(null);

        fileWorkController.createAndSetCsvFile(saveDialog, fileChooser);
        fileWorkController.writeCsvDataToFile(csvRecords);

    }
}
