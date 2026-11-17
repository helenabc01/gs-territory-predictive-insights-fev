package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrevisaoComparacaoTest {

    private final FakeSensor sensor = FakeSensor.rain();
    private final PrevisaoComparacao strategy = new PrevisaoComparacao();

    @Test
    void shouldProjectRisingTrend() throws Exception {
        List<Medicao> history = sensor.measurements(30, 30, 30, 20, 20, 20);

        double result = strategy.prever(history);

        assertEquals(40.0, result, 0.001);
    }

    @Test
    void shouldProjectFallingTrendWithoutGoingNegative() throws Exception {
        List<Medicao> history = sensor.measurements(5, 5, 5, 40, 40, 40);

        double result = strategy.prever(history);

        assertEquals(0.0, result, 0.001);
    }

    @Test
    void shouldGiveDifferentResultFromMovingAverageOnSameHistory() throws Exception {
        List<Medicao> history = sensor.measurements(30, 30, 30, 20, 20, 20);
        EstrategiaPrevisao comparison = new PrevisaoComparacao();
        EstrategiaPrevisao movingAverage = new PrevisaoMediaMovel();

        double comparisonResult = comparison.prever(history);
        double averageResult = movingAverage.prever(history);

        assertEquals(40.0, comparisonResult, 0.001);
        assertEquals(26.0, averageResult, 0.001);
    }

    @Test
    void shouldNeedTwoFullPeriods() throws Exception {
        List<Medicao> history = sensor.measurements(10, 10, 10, 10, 10);

        assertThrows(DadosInvalidosException.class, () -> strategy.prever(history));
    }
}
