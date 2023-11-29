package org.xmlconverter.conversion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.xmlconverter.converter.conversion.ConversionModel;
import org.xmlconverter.converter.flight.FlightInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConversionModelTest {
    private ConversionModel conversionModel;

    @BeforeEach
    void setUp() {
        this.conversionModel = new ConversionModel();
    }

    @Nested
    class ConvertXmlToFlightListTests {
        @Test
        public void testConvertXmlToFlightListWithValidFile() throws Exception {
            File validXmlFile = new File("src/test/resources/test-data/valid_test_data.xml");
            List<FlightInfo> flightInfoList = conversionModel.convertXmlToFlightList(validXmlFile);

            // Проверяем, что flightList не пустой и содержит ожидаемое количество элементов
            assertNotNull(flightInfoList);
            assertEquals(1, flightInfoList.size());

            // Проверяем, что данные в объектах Flight были успешно извлечены из XML
            FlightInfo firstFlightInfo = flightInfoList.get(0);
            assertEquals("рейс1", firstFlightInfo.getId());
            assertEquals("время1", firstFlightInfo.getTime());
            assertEquals("направление1", firstFlightInfo.getDestination());
            assertEquals("статус1", firstFlightInfo.getStatus());
            assertEquals("тип1", firstFlightInfo.getType());
            assertEquals("авиакомпания1", firstFlightInfo.getCompany());
        }

        @Test
        public void testConvertXmlToFlightListWithInvalidFile() {
            assertThrows(Exception.class, () -> {
                File invalidXmlFile = new File("src/test/resources/test-data/invalid_test_data.xml");
                conversionModel.convertXmlToFlightList(invalidXmlFile);
            });
        }

        @Test
        public void testConvertXmlToFlightListWithNullFile() {
            assertThrows(NullPointerException.class, () -> conversionModel.convertXmlToFlightList(null));
        }
    }

    @Nested
    class ConvertFlightListToCsvListTests {
        @Test
        void testConvertFlightListToCsvList() {
            // Создаем список Flight объектов для теста
            List<FlightInfo> flightInfoList = new ArrayList<>();
            FlightInfo flightInfo1 = new FlightInfo("рейс1", "время1", "направление1", "статус1", "тип1", "авиакомпания1");
            FlightInfo flightInfo2 = new FlightInfo("рейс2", "время2", "направление2", "статус2", "тип2", "авиакомпания2");
            flightInfoList.add(flightInfo1);
            flightInfoList.add(flightInfo2);

            // Устанавливаем ожидаемое значение для airlineName
            conversionModel.setAirlineName("авиакомпания1");

            // Вызываем тестируемую функцию
            conversionModel.convertFlightListToCsvList(flightInfoList);

            // Проверяем, что результат не равен null
            assertNotNull(conversionModel.getRecords());

            // Проверяем, что количество записей в результате соответствует ожидаемому
            assertEquals(1, conversionModel.getRecords().size());

            // Проверяем, что запись содержит правильные значения
            String[] record = conversionModel.getRecords().get(0);
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
            conversionModel.setAirlineName("авиакомпания1");
            assertThrows(NullPointerException.class,
                    () -> conversionModel.convertFlightListToCsvList( null));
            assertTrue(conversionModel.getRecords().isEmpty());
        }
    }
}


