package br.com.fiap.territorypredictiveinsightsfev.domain;

public class RiskArea extends ComponenteTerritorial {

    private static final String NORMAL = "NORMAL";

    private final String riskType;

    private final boolean floodHistory;

    private NivelAlerta currentLevel;

    public RiskArea(Long id, String name, String region, String riskType, boolean floodHistory) {
        super(id, name, region, NORMAL);
        this.riskType = riskType;
        this.floodHistory = floodHistory;
    }

    public void registerAlertLevel(NivelAlerta level) {
        if (currentLevel == null || level.compareTo(currentLevel) > 0) {
            currentLevel = level;
        }
        setStatus(verificarStatus());
    }

    @Override
    public String verificarStatus() {
        if (currentLevel == null) {
            return NORMAL;
        }
        String status = currentLevel.getDescription().toUpperCase();
        return floodHistory ? status + " - PRIORIDADE (já alagou antes)" : status;
    }

    public String getRiskType() {
        return riskType;
    }

    public boolean hasFloodHistory() {
        return floodHistory;
    }

    public NivelAlerta getCurrentLevel() {
        return currentLevel;
    }
}
