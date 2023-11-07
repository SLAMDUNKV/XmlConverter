package org.example.controllers;

import org.example.models.Flight;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Conversion {
    private static final Logger logger = Logger.getLogger(Conversion.class.getName());

    // Метод преобразует XML-файл в список объектов Flight
    public static List<Flight> convertXmlToFlightList(File xmlFile) {
        if (xmlFile == null) {
            logger.info("XML файл не выбран. Невозможно выполнить парсинг.");
            return null;
        }

        try {
            // Создание фабрики и парсера XML-документа
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Получение узлов с информацией о рейсах
            NodeList nodeList = document.getElementsByTagName("самолёт");
            List<Flight> flightList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                // Создание объектов Flight и добавление их в список
                Flight flight = new Flight(
                        element.getAttribute("рейс"),
                        element.getElementsByTagName("время").item(0).getTextContent(),
                        element.getElementsByTagName("направление").item(0).getTextContent(),
                        element.getElementsByTagName("статус").item(0).getTextContent(),
                        element.getElementsByTagName("тип").item(0).getTextContent(),
                        element.getElementsByTagName("авиакомпания").item(0).getTextContent());
                flightList.add(flight);
            }
            return flightList;
        } catch (ParserConfigurationException | IOException | NullPointerException | SAXException e) {
            logger.severe("Ошибка при парсинге XML файла!");
            return null;
        }
    }

    // Метод преобразует список объектов Flight в список массивов строк, по выбранному названию авиакомпании
    public static List<String[]> convertFlightListToCsvList(String airlineName, List<Flight> flightList) {
        if (flightList != null) {
            List<String[]> records = new ArrayList<>();

            for (Flight flight : flightList) {
                if (airlineName.equalsIgnoreCase(flight.getCompany())) {
                    String[] flightArray = new String[6];

                    // Заполнение массива строк
                    flightArray[0] = flight.getId();
                    flightArray[1] = flight.getTime();
                    flightArray[2] = flight.getDestination();
                    flightArray[3] = flight.getStatus();
                    flightArray[4] = flight.getType();
                    flightArray[5] = flight.getCompany();

                    records.add(flightArray);
                }
            }
            return records;
        }
        return null;
    }
}
