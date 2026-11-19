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

class RiverLevelSensorTest {

    @Test
    void shouldGenerateReadingWithOneDecimalPlace() throws Exception {
        // Arrange
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.50, 0.5356);
        RiverLevelSensor sensor = new RiverLevelSensor(random);

        // Act
        double value = sensor.readValue();

        // Assert
        assertEquals(421.4, value, 0.0001);
    }

    @Test
    void shouldThrowWhenSensorFails() {
        // Arrange
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.04);
        RiverLevelSensor sensor = new RiverLevelSensor(random);

        // Act
        FonteIndisponivelException error = assertThrows(
                FonteIndisponivelException.class,
                sensor::readValue
        );

        // Assert
        assertTrue(error.getMessage().contains("nível do rio"));
        verify(random, times(1)).nextDouble();
    }

    @Test
    void shouldWorkAtFailureRateBoundary() {
        // Arrange
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.05);
        RiverLevelSensor sensor = new RiverLevelSensor(random);

        // Act
        boolean working = sensor.isWorking();

        // Assert
        assertTrue(working);
    }

    @Test
    void shouldDescribeRiverLevelSensor() {
        // Arrange
        RiverLevelSensor sensor = new RiverLevelSensor();

        // Act
        SensorType type = sensor.getType();
        String unit = sensor.getUnit();
        double min = sensor.getMinValue();
        double max = sensor.getMaxValue();

        // Assert
        assertEquals(SensorType.RIVER_LEVEL, type);
        assertEquals("cm", unit);
        assertEquals(0.0, min, 0.0001);
        assertEquals(800.0, max, 0.0001);
    }

    @Test
    void shouldValidateMeasurementsUsingRiverLevelSensorLimits() {
        // Arrange
        RiverLevelSensor sensor = new RiverLevelSensor();

        // Act + Assert
        assertDoesNotThrow(() -> new Medicao(sensor, 0.0));
        assertDoesNotThrow(() -> new Medicao(sensor, 800.0));

        assertThrows(
                DadosInvalidosException.class,
                () -> new Medicao(sensor, -0.1)
        );

        assertThrows(
                DadosInvalidosException.class,
                () -> new Medicao(sensor, 800.1)
        );
    }
}
