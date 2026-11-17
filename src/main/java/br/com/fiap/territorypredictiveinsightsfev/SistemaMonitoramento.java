package br.com.fiap.territorypredictiveinsightsfev;

import br.com.fiap.territorypredictiveinsightsfev.domain.Alerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.DadosTerritorio;
import br.com.fiap.territorypredictiveinsightsfev.domain.EstrategiaPrevisao;
import br.com.fiap.territorypredictiveinsightsfev.domain.ForecastResult;
import br.com.fiap.territorypredictiveinsightsfev.domain.Medicao;
import br.com.fiap.territorypredictiveinsightsfev.domain.NivelAlerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.Sensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.SensorType;
import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;
import br.com.fiap.territorypredictiveinsightsfev.service.ConfiguracaoSistema;
import br.com.fiap.territorypredictiveinsightsfev.service.FabricaPrevisao;
import br.com.fiap.territorypredictiveinsightsfev.service.ServicoMonitoramento;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Profile("!test")
public class SistemaMonitoramento implements CommandLineRunner {

    private static final int HISTORY_PAGE_SIZE = 20;

    private final ServicoMonitoramento service;
    private final Scanner scanner = new Scanner(System.in);

    public SistemaMonitoramento(ServicoMonitoramento service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println(" TERRITORY PREDICTIVE INSIGHTS - Clima local");
        System.out.println(" Chuva intensa e risco de alagamento");
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            String option = readLine("Escolha uma opção: ");
            if (option == null) {
                break;
            }
            switch (option) {
                case "1" -> checkSensors();
                case "2" -> runForecasts();
                case "3" -> showHistory();
                case "4" -> manageTerritory();
                case "5" -> System.out.println("\n" + service.buildFullStatus());
                case "0" -> running = false;
                default -> System.out.println("Opção inválida: digite um número de 0 a 5.");
            }
        }
        System.out.println("Encerrando. O histórico de medições e alertas continua salvo no banco.");
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("----------------- MENU -----------------");
        System.out.println("1 - Verificar sensores (ler e gravar medições)");
        System.out.println("2 - Rodar previsões");
        System.out.println("3 - Ver histórico");
        System.out.println("4 - Gerenciar dados do território");
        System.out.println("5 - Status completo");
        System.out.println("0 - Sair");
    }

    private void checkSensors() {
        Integer rounds = readNumber("Quantas rodadas de leitura? (1 a 50): ", 1, 50);
        if (rounds == null) {
            return;
        }
        for (int round = 1; round <= rounds; round++) {
            System.out.println("Rodada " + round + ":");
            for (Sensor sensor : service.getSensors()) {
                readOneSensor(sensor);
            }
        }
    }

    private void readOneSensor(Sensor sensor) {
        try {
            Medicao medicao = service.readAndSave(sensor);
            System.out.println("  [OK] " + medicao);
        } catch (FonteIndisponivelException e) {
            System.out.println("  [SENSOR INDISPONÍVEL] " + e.getMessage() + ". Leitura ignorada nesta rodada.");
        } catch (DadosInvalidosException e) {
            System.out.println("  [LEITURA DESCARTADA] " + e.getMessage() + ". Nada foi gravado.");
        } catch (UnsupportedOperationException e) {
            System.out.println("  [PENDENTE] " + sensor.getClass().getSimpleName() + " ainda não foi implementado.");
        }
    }

    private void runForecasts() {
        FabricaPrevisao factory = service.getForecastFactory();
        System.out.println("1 - Estratégia configurada (" + factory.createConfigured().getName() + ")");
        System.out.println("2 - Todas as estratégias (comparar resultados)");
        Integer choice = readNumber("Escolha: ", 1, 2);
        if (choice == null) {
            return;
        }
        List<EstrategiaPrevisao> strategies = choice == 1 ? List.of(factory.createConfigured()) : factory.createAll();

        for (SensorType type : SensorType.values()) {
            for (EstrategiaPrevisao strategy : strategies) {
                try {
                    ForecastResult result = service.runForecast(type, strategy);
                    printForecast(result, strategy);
                } catch (DadosInvalidosException e) {
                    System.out.println("  [PREVISÃO SUSPENSA] " + type.getLabel() + ": " + e.getMessage());
                }
            }
        }
    }

    private void printForecast(ForecastResult result, EstrategiaPrevisao strategy) {
        String tag = result.hasAlert()
                ? "[" + result.alert().getLevel().getDescription().toUpperCase() + "]"
                : "[NORMAL]";
        System.out.printf("  %s %s | %s: %.1f %s (%s)%n", tag, result.sensorType().getLabel(),
                result.strategyName(), result.predictedValue(), result.unit(), strategy.getDescription());
        System.out.println("      -> " + result.meaning());
    }

    private void showHistory() {
        System.out.println("1 - Medições por sensor");
        System.out.println("2 - Alertas emitidos");
        Integer choice = readNumber("Escolha: ", 1, 2);
        if (choice == null) {
            return;
        }
        if (choice == 1) {
            showMeasurementHistory();
        } else {
            showAlertHistory();
        }
    }

    private void showMeasurementHistory() {
        SensorType type = chooseSensorType();
        if (type == null) {
            return;
        }
        List<Medicao> history = service.findHistoryBySensor(type);
        if (history.isEmpty()) {
            System.out.println("Nenhuma medição de " + type.getLabel() + " no banco ainda. Use a opção 1.");
            return;
        }
        int shown = Math.min(HISTORY_PAGE_SIZE, history.size());
        System.out.printf("Histórico de %s (%d mais recentes de %d):%n", type.getLabel(), shown, history.size());
        history.subList(0, shown).forEach(m -> System.out.println("  " + m));
    }

    private void showAlertHistory() {
        List<Alerta> alerts = service.listIssuedAlerts();
        if (alerts.isEmpty()) {
            System.out.println("Nenhum alerta emitido até agora.");
            return;
        }
        System.out.println("Alertas emitidos (" + alerts.size() + "):");
        alerts.forEach(a -> System.out.println("  " + a));
    }

    private void manageTerritory() {
        DadosTerritorio territory = service.getTerritoryData();
        System.out.println(territory);
        System.out.println("1 - Alterar população exposta");
        System.out.println("2 - Alterar número de pontos vulneráveis");
        System.out.println("3 - Alterar limiar de alerta (exige código de acesso)");
        System.out.println("4 - Alterar estratégia de previsão padrão");
        System.out.println("0 - Voltar");
        Integer choice = readNumber("Escolha: ", 0, 4);
        if (choice == null || choice == 0) {
            return;
        }
        try {
            switch (choice) {
                case 1 -> {
                    Integer population = readInteger("Nova população exposta: ");
                    if (population != null) {
                        territory.setExposedPopulation(population);
                        System.out.println("População atualizada.");
                    }
                }
                case 2 -> {
                    Integer points = readInteger("Novo número de pontos vulneráveis: ");
                    if (points != null) {
                        territory.setVulnerablePoints(points);
                        System.out.println("Pontos vulneráveis atualizados.");
                    }
                }
                case 3 -> changeThreshold(territory);
                case 4 -> changeDefaultStrategy();
                default -> System.out.println("Opção inválida.");
            }
        } catch (DadosInvalidosException e) {
            System.out.println("[NÃO ALTERADO] " + e.getMessage());
        }
    }

    private void changeThreshold(DadosTerritorio territory) throws DadosInvalidosException {
        SensorType type = chooseSensorType();
        if (type == null) {
            return;
        }
        System.out.println("Limiares atuais de " + type.getLabel() + ": " + ConfiguracaoSistema.getInstance().getThresholds(type));
        NivelAlerta[] levels = NivelAlerta.values();
        for (int i = 0; i < levels.length; i++) {
            System.out.println((i + 1) + " - " + levels[i].getDescription());
        }
        Integer levelIndex = readNumber("Nível: ", 1, levels.length);
        if (levelIndex == null) {
            return;
        }
        Double value = readDecimal("Novo valor do limiar: ");
        if (value == null) {
            return;
        }
        String code = readLine("Código de acesso: ");
        territory.changeThreshold(type, levels[levelIndex - 1], value, code);
        System.out.println("Limiar atualizado: " + ConfiguracaoSistema.getInstance().getThresholds(type));
    }

    private void changeDefaultStrategy() {
        FabricaPrevisao factory = service.getForecastFactory();
        List<String> types = factory.availableTypes();
        for (int i = 0; i < types.size(); i++) {
            System.out.println((i + 1) + " - " + factory.create(types.get(i)).getName());
        }
        Integer index = readNumber("Estratégia: ", 1, types.size());
        if (index != null) {
            ConfiguracaoSistema.getInstance().setDefaultStrategy(types.get(index - 1));
            System.out.println("Estratégia padrão: " + factory.createConfigured().getName());
        }
    }

    private SensorType chooseSensorType() {
        SensorType[] types = SensorType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " - " + types[i].getLabel());
        }
        Integer index = readNumber("Sensor: ", 1, types.length);
        return index == null ? null : types[index - 1];
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine().trim();
    }

    private Integer readNumber(String prompt, int min, int max) {
        Integer number = readInteger(prompt);
        if (number != null && (number < min || number > max)) {
            System.out.printf("Valor fora das opções: digite entre %d e %d.%n", min, max);
            return null;
        }
        return number;
    }

    private Integer readInteger(String prompt) {
        String input = readLine(prompt);
        if (input == null) {
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida: \"" + input + "\" não é um número inteiro.");
            return null;
        }
    }

    private Double readDecimal(String prompt) {
        String input = readLine(prompt);
        if (input == null) {
            return null;
        }
        try {
            return Double.parseDouble(input.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida: \"" + input + "\" não é um número.");
            return null;
        }
    }
}
