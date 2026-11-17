package br.com.fiap.territorypredictiveinsightsfev.servico;

import br.com.fiap.territorypredictiveinsightsfev.persistencia.AlertaRepository;
import br.com.fiap.territorypredictiveinsightsfev.persistencia.MedicaoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Testes do serviço com Mockito: os repositories são FALSOS (mocks),
 * então a lógica de alerta é testada sem banco de dados.
 *
 * Responsável: Integrante 3 (previsões, alertas e testes)
 */
@ExtendWith(MockitoExtension.class)
class ServicoMonitoramentoTest {

    @Mock
    private MedicaoRepository medicaoRepository;

    @Mock
    private AlertaRepository alertaRepository;

    @InjectMocks
    private ServicoMonitoramento servico;

    // TODO: deveEmitirAlertaCriticoQuandoPrevisaoPassaDoLimite()
    //       Arrange: when(medicaoRepository.find...(...)).thenReturn(lista com chuva alta)
    //       Act: servico.rodarPrevisao(...) / avaliarAlerta(...)
    //       Assert: verify(alertaRepository).save(any(Alerta.class)) + conferir o nível

    // TODO: naoDeveEmitirAlertaQuandoPrevisaoAbaixoDoLimite()
    //       Assert: verify(alertaRepository, never()).save(any())
}
