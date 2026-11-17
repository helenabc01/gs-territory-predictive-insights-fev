package br.com.fiap.territorypredictiveinsightsfev.domain;

public enum NivelAlerta {
    ATENCAO("Atenção", "acompanhar a evolução e vistoriar bueiros e bocas de lobo"),
    ALERTA("Alerta", "avisar moradores das áreas de risco e deixar equipes de prontidão"),
    CRITICO("Crítico", "acionar a Defesa Civil e abrir pontos de apoio para desalojados");

    private final String description;

    private final String recommendedAction;

    NivelAlerta(String description, String recommendedAction) {
        this.description = description;
        this.recommendedAction = recommendedAction;
    }

    public String getDescription() {
        return description;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }
}
