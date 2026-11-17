package br.com.fiap.territorypredictiveinsightsfev.domain;

/**
 * Testes unitários de Medicao (sem banco). Padrão AAA: Arrange, Act, Assert.
 */
class MedicaoTest {

    // TODO: shouldCreateValidMeasurement()
    //       Arrange: sensor falso (faixa 0–150) + valor 20.0 | Act: new Medicao(sensor, 20.0)
    //       Assert: assertEquals(20.0, medicao.getValue()) e sensorType/unit copiados

    // TODO: shouldThrowWhenValueBelowMinimum()
    //       assertThrows(DadosInvalidosException.class, () -> new Medicao(sensor, -5.0))

    // TODO: shouldThrowWhenValueAboveMaximum()
}
