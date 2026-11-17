package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import br.com.fiap.territorypredictiveinsightsfev.service.ConfiguracaoSistema;

public class DadosTerritorio {

    private String region;
    private int exposedPopulation;
    private int vulnerablePoints;
    private final String accessCode;

    public DadosTerritorio(String region, int exposedPopulation, int vulnerablePoints, String accessCode) {
        if (region == null || region.isBlank() || accessCode == null || accessCode.isBlank()
                || exposedPopulation < 0 || vulnerablePoints < 0) {
            throw new IllegalArgumentException("dados iniciais do território inválidos");
        }
        this.region = region;
        this.exposedPopulation = exposedPopulation;
        this.vulnerablePoints = vulnerablePoints;
        this.accessCode = accessCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) throws DadosInvalidosException {
        if (region == null || region.isBlank()) {
            throw new DadosInvalidosException("região inválida: o nome da região não pode ficar vazio");
        }
        this.region = region.trim();
    }

    public int getExposedPopulation() {
        return exposedPopulation;
    }

    public void setExposedPopulation(int exposedPopulation) throws DadosInvalidosException {
        if (exposedPopulation < 0) {
            throw new DadosInvalidosException("população negativa: " + exposedPopulation);
        }
        this.exposedPopulation = exposedPopulation;
    }

    public int getVulnerablePoints() {
        return vulnerablePoints;
    }

    public void setVulnerablePoints(int vulnerablePoints) throws DadosInvalidosException {
        if (vulnerablePoints < 0) {
            throw new DadosInvalidosException("número de pontos vulneráveis negativo: " + vulnerablePoints);
        }
        this.vulnerablePoints = vulnerablePoints;
    }

    public void changeThreshold(SensorType sensorType, NivelAlerta level, double newValue, String informedCode)
            throws DadosInvalidosException {
        if (!accessCode.equals(informedCode)) {
            throw new DadosInvalidosException("código de acesso incorreto: o limiar não foi alterado");
        }
        ConfiguracaoSistema.getInstance().setThreshold(sensorType, level, newValue);
    }

    @Override
    public String toString() {
        return String.format("Região: %s | População exposta: %d | Pontos vulneráveis: %d",
                region, exposedPopulation, vulnerablePoints);
    }
}
