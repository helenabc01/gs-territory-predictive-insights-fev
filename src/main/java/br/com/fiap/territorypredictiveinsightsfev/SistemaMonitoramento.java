package br.com.fiap.territorypredictiveinsightsfev;

import br.com.fiap.territorypredictiveinsightsfev.service.ServicoMonitoramento;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SistemaMonitoramento implements CommandLineRunner {

    private final ServicoMonitoramento service;
    private final Scanner scanner = new Scanner(System.in);

    public SistemaMonitoramento(ServicoMonitoramento service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        // TODO: loop principal com scanner e as opções:
        //       1 - Verificar sensores (ler e gravar medições)
        //       2 - Rodar previsões
        //       3 - Ver histórico (medições por sensor / alertas emitidos)
        //       4 - Gerenciar dados do território (alterar limiares com código de acesso)
        //       5 - Status completo
        //       0 - Sair
        // TODO: capturar DadosInvalidosException / FonteIndisponivelException aqui,
        //       mostrando mensagem clara para o usuário. NENHUM catch vazio.
        // TODO: ATENÇÃO: quando o loop entrar, o teste contextLoads() vai tentar rodar o menu.
        //       Resolver com o grupo (ex.: @Profile("!test") nesta classe, ou remover contextLoads()).
    }
}
