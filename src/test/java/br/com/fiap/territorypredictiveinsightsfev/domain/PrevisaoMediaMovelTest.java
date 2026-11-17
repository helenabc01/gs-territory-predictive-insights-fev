package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrevisaoMediaMovelTest {

    private final FakeSensor sensor = FakeSensor.rain();
    private final PrevisaoMediaMovel strategy = new PrevisaoMediaMovel();

    @Test
    void shouldAverageTheFiveMostRecentReadings() throws Exception {
        List<Medicao> history = sensor.measurements(10, 20, 30, 40, 50, 100, 100);

        double result = strategy.prever(history);

        assertEquals(30.0, result, 0.001);
    }

    @Test
    void shouldReturnTheSameValueWhenAllReadingsAreEqual() throws Exception {
        List<Medicao> history = sensor.measurements(12, 12, 12, 12, 12);

        double result = strategy.prever(history);

        assertEquals(12.0, result, 0.001);
    }

    @Test
    void shouldRefuseToForecastWithTooFewReadings() throws Exception {
        List<Medicao> history = sensor.measurements(10, 20, 30, 40);

        assertThrows(DadosInvalidosException.class, () -> strategy.prever(history));
    }

    @Test
    void shouldRefuseToForecastWithEmptyHistory() {
        List<Medicao> history = List.of();

        assertThrows(DadosInvalidosException.class, () -> strategy.prever(history));
    }
}
