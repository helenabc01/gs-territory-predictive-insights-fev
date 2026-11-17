package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.Alerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.FakeSensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.ForecastResult;
import br.com.fiap.territorypredictiveinsightsfev.domain.Medicao;
import br.com.fiap.territorypredictiveinsightsfev.domain.NivelAlerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.PrevisaoMediaMovel;
import br.com.fiap.territorypredictiveinsightsfev.domain.SensorType;
import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;
import br.com.fiap.territorypredictiveinsightsfev.persistence.AlertaRepository;
import br.com.fiap.territorypredictiveinsightsfev.persistence.MedicaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicoMonitoramentoTest {

    @Mock
    private MedicaoRepository medicaoRepository;

    @Mock
    private AlertaRepository alertaRepository;

    @InjectMocks
    private ServicoMonitoramento service;

    private final FakeSensor rain = FakeSensor.rain();

    @BeforeEach
    void restoreDefaults() {
        ConfiguracaoSistema.getInstance().restaurarPadroes();
    }

    @Test
    void shouldIssueCriticalAlertWhenForecastExceedsThreshold() throws Exception {
        List<Medicao> history = rain.measurements(60, 55, 52, 58, 60);
        when(medicaoRepository.findBySensorTypeOrderByIdDesc(SensorType.RAIN)).thenReturn(history);

        ForecastResult result = service.runForecast(SensorType.RAIN, new PrevisaoMediaMovel());

        ArgumentCaptor<Alerta> saved = ArgumentCaptor.forClass(Alerta.class);
        verify(alertaRepository).save(saved.capture());
        assertEquals(NivelAlerta.CRITICO, saved.getValue().getLevel());
        assertEquals(50.0, saved.getValue().getExceededThreshold(), 0.001);
        assertEquals(57.0, result.predictedValue(), 0.001);
        assertTrue(result.hasAlert());
    }

    @Test
    void shouldIssueAttentionAlertBetweenAttentionAndAlertThresholds() throws Exception {
        when(medicaoRepository.findBySensorTypeOrderByIdDesc(SensorType.RAIN))
                .thenReturn(rain.measurements(20, 20, 20, 20, 20));

        ForecastResult result = service.runForecast(SensorType.RAIN, new PrevisaoMediaMovel());

        assertEquals(NivelAlerta.ATENCAO, result.alert().getLevel());
    }

    @Test
    void shouldNotIssueAlertWhenForecastIsBelowThresholds() throws Exception {
        when(medicaoRepository.findBySensorTypeOrderByIdDesc(SensorType.RAIN))
                .thenReturn(rain.measurements(1, 2, 3, 4, 5));

        ForecastResult result = service.runForecast(SensorType.RAIN, new PrevisaoMediaMovel());

        assertFalse(result.hasAlert());
        verify(alertaRepository, never()).save(any());
    }

    @Test
    void shouldStaySilentWithInsufficientHistory() throws Exception {
        when(medicaoRepository.findBySensorTypeOrderByIdDesc(SensorType.RAIN))
                .thenReturn(rain.measurements(140, 140));

        assertThrows(DadosInvalidosException.class,
                () -> service.runForecast(SensorType.RAIN, new PrevisaoMediaMovel()));
        verify(alertaRepository, never()).save(any());
    }

    @Test
    void shouldSaveValidReading() throws Exception {
        rain.willRead(25.0);

        Medicao medicao = service.readAndSave(rain);

        verify(medicaoRepository).save(medicao);
        assertEquals(25.0, medicao.getValue(), 0.001);
    }

    @Test
    void shouldNotSaveInvalidReading() {
        rain.willRead(-5.0);

        assertThrows(DadosInvalidosException.class, () -> service.readAndSave(rain));
        verify(medicaoRepository, never()).save(any());
    }

    @Test
    void shouldNotSaveWhenSensorIsUnavailable() {
        rain.broken();

        assertThrows(FonteIndisponivelException.class, () -> service.readAndSave(rain));
        verify(medicaoRepository, never()).save(any());
    }
}
