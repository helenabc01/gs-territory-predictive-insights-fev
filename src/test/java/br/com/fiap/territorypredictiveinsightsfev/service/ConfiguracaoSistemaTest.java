package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.NivelAlerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.SensorType;
import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfiguracaoSistemaTest {

    private final ConfiguracaoSistema config = ConfiguracaoSistema.getInstance();

    @AfterEach
    void restoreDefaults() {
        config.restaurarPadroes();
    }

    @Test
    void shouldAlwaysReturnTheSameInstance() {
        ConfiguracaoSistema first = ConfiguracaoSistema.getInstance();
        ConfiguracaoSistema second = ConfiguracaoSistema.getInstance();

        assertSame(first, second);
    }

    @Test
    void shouldRejectNegativeThreshold() {
        assertThrows(DadosInvalidosException.class,
                () -> config.setThreshold(SensorType.RAIN, NivelAlerta.ATENCAO, -1));
    }

    @Test
    void shouldRejectThresholdThatBreaksLevelOrder() {
        assertThrows(DadosInvalidosException.class,
                () -> config.setThreshold(SensorType.RAIN, NivelAlerta.ATENCAO, 40));
        assertEquals(10.0, config.getThreshold(SensorType.RAIN, NivelAlerta.ATENCAO), 0.001);
    }
}
