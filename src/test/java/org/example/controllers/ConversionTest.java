package org.example.controllers;

import org.example.models.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.example.controllers.Conversion.convertFlightListToCsvList;
import static org.example.controllers.Conversion.convertXmlToFlightList;
import static org.junit.jupiter.api.Assertions.*;

public class ConversionTest {
    @Nested
    class ConvertXmlToFlightListTests {
        @Test
        public void testConvertXmlToFlightListWithValidFile() {
            File validXmlFile = new File("src/test/resources/test-data/valid_test_data.xml");
            List<Flight> flightList = convertXmlToFlightList(validXmlFile);

            // Проверяем, что flightList не пустой и содержит ожидаемое количество элементов
            assertNotNull(flightList);
            assertEquals(1, flightList.size());

            // Проверяем, что данные в объектах Flight были успешно извлечены из XML
            Flight firstFlight = flightList.get(0);
            assertEquals("рейс1", firstFlight.getId());
            assertEquals("время1", firstFlight.getTime());
            assertEquals("направление1", firstFlight.getDestination());
            assertEquals("статус1", firstFlight.getStatus());
            assertEquals("тип1", firstFlight.getType());
            assertEquals("авиакомпания1", firstFlight.getCompany());
        }

        @Test
        public void testConvertXmlToFlightListWithInvalidFile() {
            File invalidXmlFile = new File("src/test/resources/test-data/invalid_test_data.xml");
            List<Flight> flightList = convertXmlToFlightList(invalidXmlFile);

            // Проверяем, что функция вернула null из-за ошибки в файле
            assertNull(flightList);
        }

        @Test
        public void testConvertXmlToFlightListWithNullFile() {
            List<Flight> flightList = convertXmlToFlightList(null);

            // Проверяем, что функция вернула null из-за нулевого аргумента
            assertNull(flightList);
        }
    }

    @Nested
    class ConvertFlightListToCsvListTests {
        @Test
        void testConvertFlightListToCsvList() {
            // Создаем список Flight объектов для теста
            List<Flight> flightList = new ArrayList<>();
            Flight flight1 = new Flight("рейс1", "время1", "направление1", "статус1", "тип1", "авиакомпания1");
            Flight flight2 = new Flight("рейс2", "время2", "направление2", "статус2", "тип2", "авиакомпания2");
            flightList.add(flight1);
            flightList.add(flight2);

            // Устанавливаем ожидаемое значение для airlineName
            String expectedAirlineName = "авиакомпания1";

            // Вызываем тестируемую функцию
            List<String[]> records = convertFlightListToCsvList(expectedAirlineName, flightList);

            // Проверяем, что результат не равен null
            assertNotNull(records);

            // Проверяем, что количество записей в результате соответствует ожидаемому
            assertEquals(1, records.size());

            // Проверяем, что запись содержит правильные значения
            String[] record = records.get(0);
            assertEquals("рейс1", record[0]);
            assertEquals("время1", record[1]);
            assertEquals("направление1", record[2]);
            assertEquals("статус1", record[3]);
            assertEquals("тип1", record[4]);
            assertEquals("авиакомпания1", record[5]);
        }

        @Test
        public void testConvertFlightListToCsvListWithNullList() {
            // Вызываем тестируемую функцию с null входным списком
            List<String[]> records = convertFlightListToCsvList("авиакомпания1", null);

            // Проверяем, что результат равен null
            assertNull(records);
        }
    }
}


