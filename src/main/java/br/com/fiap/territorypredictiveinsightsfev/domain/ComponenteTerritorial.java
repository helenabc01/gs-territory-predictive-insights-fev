package br.com.fiap.territorypredictiveinsightsfev.domain;

public abstract class ComponenteTerritorial {

    private final Long id;
    private final String name;
    private final String region;
    private String status;

    protected ComponenteTerritorial(Long id, String name, String region, String status) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.status = status;
    }

    public abstract String verificarStatus();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %s", name, region, status);
    }
}
