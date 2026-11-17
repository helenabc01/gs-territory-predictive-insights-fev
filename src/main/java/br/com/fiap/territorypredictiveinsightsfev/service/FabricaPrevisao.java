package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.EstrategiaPrevisao;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Padrão criacional FACTORY: cria a EstrategiaPrevisao a partir da configuração,
 * SEM if tipo == / instanceof espalhado pelo código.
 * Nome exigido pelo enunciado. Justificar no README por que Factory aqui.
 */
public class FabricaPrevisao {

    /** Nome da estratégia -> como criá-la. É o mapa que substitui os ifs. */
    private final Map<String, Supplier<EstrategiaPrevisao>> creators = new HashMap<>();

    // TODO: construtor registrando as estratégias:
    //       creators.put("MOVING_AVERAGE", PrevisaoMediaMovel::new);
    //       creators.put("COMPARISON", PrevisaoComparacao::new);
    // TODO: EstrategiaPrevisao create(String type)
    //       tipo desconhecido -> exceção com mensagem clara
    // TODO (opcional): List<EstrategiaPrevisao> createAll() para rodar as duas no menu
}
