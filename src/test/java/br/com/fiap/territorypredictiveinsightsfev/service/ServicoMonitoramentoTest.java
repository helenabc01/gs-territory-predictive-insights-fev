package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.persistence.AlertaRepository;
import br.com.fiap.territorypredictiveinsightsfev.persistence.MedicaoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Testes do serviço com Mockito: os repositories são FALSOS (mocks),
 * então a lógica de alerta é testada sem banco de dados.
 */
@ExtendWith(MockitoExtension.class)
class ServicoMonitoramentoTest {

    @Mock
    private MedicaoRepository medicaoRepository;

    @Mock
    private AlertaRepository alertaRepository;

    @InjectMocks
    private ServicoMonitoramento service;

    // TODO: shouldIssueCriticalAlertWhenForecastExceedsThreshold()
    //       Arrange: when(medicaoRepository.findBySensorType...(...)).thenReturn(lista com chuva alta)
    //       Act: service.runForecast(...) / evaluateAlert(...)
    //       Assert: verify(alertaRepository).save(any(Alerta.class)) + conferir o nível

    // TODO: shouldNotIssueAlertWhenForecastBelowThreshold()
    //       Assert: verify(alertaRepository, never()).save(any())
}
