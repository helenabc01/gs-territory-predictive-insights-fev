package br.com.fiap.territorypredictiveinsightsfev.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RiskAreaTest {

    @Test
    void shouldStartNormal() {
        ComponenteTerritorial area = new RiskArea(1L, "Área", "Região", "Alagamento", false);

        String status = area.verificarStatus();

        assertEquals("NORMAL", status);
    }

    @Test
    void shouldKeepTheMostSevereLevel() {
        RiskArea area = new RiskArea(1L, "Área", "Região", "Alagamento", false);

        area.registerAlertLevel(NivelAlerta.CRITICO);
        area.registerAlertLevel(NivelAlerta.ATENCAO);

        assertEquals(NivelAlerta.CRITICO, area.getCurrentLevel());
        assertEquals("CRÍTICO", area.getStatus());
    }

    @Test
    void shouldPrioritizeAreaWithFloodHistory() {
        RiskArea area = new RiskArea(1L, "Área", "Região", "Alagamento", true);

        area.registerAlertLevel(NivelAlerta.ALERTA);

        assertTrue(area.verificarStatus().contains("PRIORIDADE"));
    }
}
