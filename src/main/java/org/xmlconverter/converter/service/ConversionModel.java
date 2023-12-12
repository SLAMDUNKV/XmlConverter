package org.xmlconverter.converter.service;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ConversionModel {
    @Getter
    private final List<String[]> records;
    @Setter
    private String airlineName;

    public ConversionModel() {
        this.records = new ArrayList<>();
    }

    // Метод преобразует XML-файл в список объектов Flight
    public List<FlightInfo> convertXmlToFlightList(@NonNull File xmlFile) throws Exception {
        // Создание фабрики и парсера XML-документа
        val factory = DocumentBuilderFactory.newInstance();
        val builder = factory.newDocumentBuilder();
        val document = builder.parse(xmlFile);
        // Получение узлов с информацией о рейсах
        val nodeList = document.getElementsByTagName("самолёт");
        List<FlightInfo> flightInfoList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            val element = (Element) nodeList.item(i);
            // Создание объектов Flight и добавление их в список
            val flightInfo = new FlightInfo(element.getAttribute("рейс"),
                    element.getElementsByTagName("время").item(0).getTextContent(),
                    element.getElementsByTagName("направление").item(0).getTextContent(),
                    element.getElementsByTagName("статус").item(0).getTextContent(),
                    element.getElementsByTagName("тип").item(0).getTextContent(),
                    element.getElementsByTagName("авиакомпания").item(0).getTextContent());
            flightInfoList.add(flightInfo);
        }
        return flightInfoList;
    }

    // Метод преобразует список объектов Flight в список массивов строк, по выбранному названию авиакомпании
    public void convertFlightListToCsvList(@NonNull List<FlightInfo> flightInfoList) {
        for (FlightInfo flightInfo : flightInfoList) {
            if (airlineName.equalsIgnoreCase(flightInfo.getCompany())) {
                String[] flightArray = new String[6];
                // Заполнение массива строк
                flightArray[0] = flightInfo.getId();
                flightArray[1] = flightInfo.getTime();
                flightArray[2] = flightInfo.getDestination();
                flightArray[3] = flightInfo.getStatus();
                flightArray[4] = flightInfo.getType();
                flightArray[5] = flightInfo.getCompany();
                this.records.add(flightArray);
            }
        }
    }
}
