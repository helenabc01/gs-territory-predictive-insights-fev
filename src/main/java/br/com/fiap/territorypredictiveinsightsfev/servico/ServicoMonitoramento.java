package br.com.fiap.territorypredictiveinsightsfev.servico;

import br.com.fiap.territorypredictiveinsightsfev.persistencia.AlertaRepository;
import br.com.fiap.territorypredictiveinsightsfev.persistencia.MedicaoRepository;
import org.springframework.stereotype.Service;

/**
 * Regras de negócio: ler sensores, gravar medições, rodar previsões e gerar alertas.
 * O menu só chama este serviço; aqui não tem Scanner nem System.in.
 *
 * Os repositories chegam pelo construtor, o que permite mockar com Mockito
 * no ServicoMonitoramentoTest (testar alerta sem banco).
 *
 * Responsáveis: Integrante 2 (gravar/listar) + Integrante 3 (previsão/alerta)
 */
@Service
public class ServicoMonitoramento {

    private final MedicaoRepository medicaoRepository;
    private final AlertaRepository alertaRepository;

    public ServicoMonitoramento(MedicaoRepository medicaoRepository, AlertaRepository alertaRepository) {
        this.medicaoRepository = medicaoRepository;
        this.alertaRepository = alertaRepository;
    }

    // ---- Sensores e leituras (Integrante 2) ----
    // TODO: registrarLeitura(Sensor sensor)
    //       lê o valor -> cria Medicao (valida) -> medicaoRepository.save(...)
    //       propaga DadosInvalidosException / FonteIndisponivelException para o menu tratar

    // ---- Histórico (Integrante 2) ----
    // TODO: buscarHistoricoPorSensor(String tipoSensor)
    // TODO: listarAlertasEmitidos()

    // ---- Previsões e alertas (Integrante 3) ----
    // TODO: rodarPrevisao(String tipoSensor, EstrategiaPrevisao estrategia)
    //       busca histórico no banco -> estrategia.prever(historico)
    // TODO: avaliarAlerta(String tipoSensor, double valorPrevisto)
    //       compara com limiares do ConfiguracaoSistema -> decide NivelAlerta
    //       se estourou: cria Alerta com o PORQUÊ (sensor, valor previsto, limite) -> alertaRepository.save(...)

    // ---- Status completo (qualquer um) ----
    // TODO: gerarStatusCompleto() -> resumo de sensores, últimas previsões e alertas
    //       (opcional: usar um RelatorioBuilder aqui, se quiserem um 2º padrão)
}
