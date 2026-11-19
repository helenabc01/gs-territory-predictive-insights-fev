package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RainSensorTest {

    @Test
    void shouldGenerateReadingWithOneDecimalPlace() throws Exception {
        // Arrange
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.50, 0.5356);
        RainSensor sensor = new RainSensor(random);

        // Act
        double value = sensor.readValue();

        // Assert
        assertEquals(42.8, value, 0.0001);
    }

    @Test
    void shouldThrowWhenSensorFails() {
        // Arrange
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.04);
        RainSensor sensor = new RainSensor(random);

        // Act
        FonteIndisponivelException error = assertThrows(
                FonteIndisponivelException.class,
                sensor::readValue
        );

        // Assert
        assertTrue(error.getMessage().contains("pluviômetro"));
        verify(random, times(1)).nextDouble();
    }

    @Test
    void shouldWorkAtFailureRateBoundary() {
        // Arrange
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.05);
        RainSensor sensor = new RainSensor(random);

        // Act
        boolean working = sensor.isWorking();

        // Assert
        assertTrue(working);
    }

    @Test
    void shouldDescribeRainSensor() {
        // Arrange
        RainSensor sensor = new RainSensor();

        // Act
        SensorType type = sensor.getType();
        String unit = sensor.getUnit();
        double min = sensor.getMinValue();
        double max = sensor.getMaxValue();

        // Assert
        assertEquals(SensorType.RAIN, type);
        assertEquals("mm/h", unit);
        assertEquals(0.0, min, 0.0001);
        assertEquals(150.0, max, 0.0001);
    }

    @Test
    void shouldValidateMeasurementsUsingRainSensorLimits() {
        // Arrange
        RainSensor sensor = new RainSensor();

        // Act + Assert
        assertDoesNotThrow(() -> new Medicao(sensor, 0.0));
        assertDoesNotThrow(() -> new Medicao(sensor, 150.0));

        assertThrows(
                DadosInvalidosException.class,
                () -> new Medicao(sensor, -0.1)
        );

        assertThrows(
                DadosInvalidosException.class,
                () -> new Medicao(sensor, 150.1)
        );
    }
}