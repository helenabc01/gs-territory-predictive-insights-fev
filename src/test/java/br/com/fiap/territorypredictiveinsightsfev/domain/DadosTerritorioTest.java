package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import br.com.fiap.territorypredictiveinsightsfev.service.ConfiguracaoSistema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DadosTerritorioTest {

    private static final String CODE = "1234";

    private final DadosTerritorio territory = new DadosTerritorio("Região teste", 1000, 2, CODE);

    @AfterEach
    void restoreThresholds() {
        ConfiguracaoSistema.getInstance().restaurarPadroes();
    }

    @Test
    void shouldRejectNegativePopulation() {
        assertThrows(DadosInvalidosException.class, () -> territory.setExposedPopulation(-1));
        assertEquals(1000, territory.getExposedPopulation());
    }

    @Test
    void shouldNotChangeThresholdWithWrongAccessCode() {
        double before = ConfiguracaoSistema.getInstance().getThreshold(SensorType.RAIN, NivelAlerta.CRITICO);

        assertThrows(DadosInvalidosException.class,
                () -> territory.changeThreshold(SensorType.RAIN, NivelAlerta.CRITICO, 60, "senha-errada"));

        assertEquals(before, ConfiguracaoSistema.getInstance().getThreshold(SensorType.RAIN, NivelAlerta.CRITICO));
    }

    @Test
    void shouldChangeThresholdWithCorrectAccessCode() throws Exception {
        territory.changeThreshold(SensorType.RAIN, NivelAlerta.CRITICO, 60, CODE);

        assertEquals(60.0, ConfiguracaoSistema.getInstance().getThreshold(SensorType.RAIN, NivelAlerta.CRITICO), 0.001);
    }
}
