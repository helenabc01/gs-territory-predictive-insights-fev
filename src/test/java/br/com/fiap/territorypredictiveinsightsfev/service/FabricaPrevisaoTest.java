package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.EstrategiaPrevisao;
import br.com.fiap.territorypredictiveinsightsfev.domain.PrevisaoComparacao;
import br.com.fiap.territorypredictiveinsightsfev.domain.PrevisaoMediaMovel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricaPrevisaoTest {

    private final FabricaPrevisao factory = new FabricaPrevisao();

    @AfterEach
    void restoreDefaults() {
        ConfiguracaoSistema.getInstance().restaurarPadroes();
    }

    @Test
    void shouldCreateStrategyByName() {
        EstrategiaPrevisao movingAverage = factory.create(FabricaPrevisao.MOVING_AVERAGE);
        EstrategiaPrevisao comparison = factory.create(FabricaPrevisao.COMPARISON);

        assertInstanceOf(PrevisaoMediaMovel.class, movingAverage);
        assertInstanceOf(PrevisaoComparacao.class, comparison);
    }

    @Test
    void shouldCreateStrategyFromConfiguration() {
        ConfiguracaoSistema.getInstance().setDefaultStrategy(FabricaPrevisao.COMPARISON);

        EstrategiaPrevisao strategy = factory.createConfigured();

        assertInstanceOf(PrevisaoComparacao.class, strategy);
    }

    @Test
    void shouldRejectUnknownStrategy() {
        assertThrows(IllegalArgumentException.class, () -> factory.create("BOLA_DE_CRISTAL"));
    }

    @Test
    void shouldCreateOneInstanceOfEachStrategy() {
        assertEquals(2, factory.createAll().size());
    }
}
