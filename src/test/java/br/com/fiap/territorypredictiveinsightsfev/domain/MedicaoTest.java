package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MedicaoTest {

    @Test
    void shouldCreateValidMeasurementCopyingSensorData() throws Exception {
        FakeSensor sensor = FakeSensor.rain();

        Medicao medicao = new Medicao(sensor, 20.0);

        assertEquals(20.0, medicao.getValue(), 0.001);
        assertEquals(SensorType.RAIN, medicao.getSensorType());
        assertEquals("mm/h", medicao.getUnit());
        assertNotNull(medicao.getMeasuredAt());
    }

    @Test
    void shouldAcceptValuesExactlyOnTheLimits() throws Exception {
        FakeSensor sensor = FakeSensor.rain();

        Medicao atMinimum = new Medicao(sensor, 0.0);
        Medicao atMaximum = new Medicao(sensor, 150.0);

        assertEquals(0.0, atMinimum.getValue(), 0.001);
        assertEquals(150.0, atMaximum.getValue(), 0.001);
    }

    @Test
    void shouldThrowWhenRainIsNegative() {
        FakeSensor sensor = FakeSensor.rain();

        DadosInvalidosException exception =
                assertThrows(DadosInvalidosException.class, () -> new Medicao(sensor, -3.0));

        assertTrue(exception.getMessage().contains("fora da faixa"));
    }

    @Test
    void shouldThrowWhenValueIsAboveMaximum() {
        FakeSensor sensor = FakeSensor.rain();

        assertThrows(DadosInvalidosException.class, () -> new Medicao(sensor, 150.1));
    }

    @Test
    void shouldThrowWhenValueIsNotANumber() {
        FakeSensor sensor = FakeSensor.rain();

        assertThrows(DadosInvalidosException.class, () -> new Medicao(sensor, Double.NaN));
    }
}
