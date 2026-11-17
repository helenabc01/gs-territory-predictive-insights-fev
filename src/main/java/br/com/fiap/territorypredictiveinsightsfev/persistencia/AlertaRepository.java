package br.com.fiap.territorypredictiveinsightsfev.persistencia;

import br.com.fiap.territorypredictiveinsightsfev.dominio.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Acesso ao banco para os alertas emitidos (Spring Data JPA).
 *
 * Responsável: Integrante 2 (persistência)
 */
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    // TODO: listar alertas mais recentes primeiro, ex.:
    //       List<Alerta> findAllByOrderByDataHoraDesc();

    // TODO (opcional): filtrar por nível, ex.:
    //       List<Alerta> findByNivel(NivelAlerta nivel);
}
