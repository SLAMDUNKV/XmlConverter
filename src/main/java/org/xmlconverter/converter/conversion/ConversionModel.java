package org.xmlconverter.converter.conversion;

import lombok.Getter;
import lombok.NonNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlconverter.converter.flight.FlightInfo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ConversionModel {
    private final List<String[]> records;

    public ConversionModel() {
        this.records = new ArrayList<>();
    }

    // Метод преобразует XML-файл в список объектов Flight
    public List<FlightInfo> convertXmlToFlightList(@NonNull File xmlFile) throws Exception {
        // Создание фабрики и парсера XML-документа
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);

        // Получение узлов с информацией о рейсах
        NodeList nodeList = document.getElementsByTagName("самолёт");
        List<FlightInfo> flightInfoList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            // Создание объектов Flight и добавление их в список
            FlightInfo flightInfo = new FlightInfo(
                    element.getAttribute("рейс"),
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
    public void convertFlightListToCsvList(String airlineName, @NonNull List<FlightInfo> flightInfoList) {
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
