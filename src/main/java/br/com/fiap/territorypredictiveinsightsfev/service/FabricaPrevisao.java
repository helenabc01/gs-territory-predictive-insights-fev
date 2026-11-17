package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.EstrategiaPrevisao;
import br.com.fiap.territorypredictiveinsightsfev.domain.PrevisaoComparacao;
import br.com.fiap.territorypredictiveinsightsfev.domain.PrevisaoMediaMovel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class FabricaPrevisao {

    public static final String MOVING_AVERAGE = "MOVING_AVERAGE";
    public static final String COMPARISON = "COMPARISON";

    private final Map<String, Supplier<EstrategiaPrevisao>> creators = new LinkedHashMap<>();

    public FabricaPrevisao() {
        creators.put(MOVING_AVERAGE, PrevisaoMediaMovel::new);
        creators.put(COMPARISON, PrevisaoComparacao::new);
    }

    public EstrategiaPrevisao create(String type) {
        return Optional.ofNullable(creators.get(type))
                .orElseThrow(() -> new IllegalArgumentException(
                        "estratégia de previsão desconhecida: " + type + ". Opções: " + creators.keySet()))
                .get();
    }

    public EstrategiaPrevisao createConfigured() {
        return create(ConfiguracaoSistema.getInstance().getDefaultStrategy());
    }

    public List<EstrategiaPrevisao> createAll() {
        return creators.values().stream().map(Supplier::get).toList();
    }

    public List<String> availableTypes() {
        return List.copyOf(creators.keySet());
    }
}
