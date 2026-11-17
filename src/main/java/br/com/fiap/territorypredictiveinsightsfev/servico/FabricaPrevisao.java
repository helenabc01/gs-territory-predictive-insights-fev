package br.com.fiap.territorypredictiveinsightsfev.servico;

/**
 * Padrão criacional FACTORY: cria a EstrategiaPrevisao a partir da configuração,
 * SEM if tipo == / instanceof espalhado pelo código.
 * Justificar no README por que Factory aqui.
 *
 * Responsável: Integrante 3 (previsões, alertas e testes)
 */
public class FabricaPrevisao {

    // TODO: mapa nome -> criador, ex.: Map<String, Supplier<EstrategiaPrevisao>>
    //       "MEDIA_MOVEL" -> PrevisaoMediaMovel::new
    //       "COMPARACAO"  -> PrevisaoComparacao::new
    //       (ou um enum TipoPrevisao em que cada constante sabe criar a sua estratégia)

    // TODO: EstrategiaPrevisao criar(String tipo)
    //       tipo desconhecido -> exceção com mensagem clara

    // TODO (opcional): List<EstrategiaPrevisao> criarTodas() para rodar as duas no menu
}
