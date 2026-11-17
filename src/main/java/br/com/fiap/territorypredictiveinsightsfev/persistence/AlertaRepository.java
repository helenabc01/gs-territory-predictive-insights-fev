package br.com.fiap.territorypredictiveinsightsfev.persistence;

import br.com.fiap.territorypredictiveinsightsfev.domain.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Acesso ao banco para os alertas emitidos (Spring Data JPA).
 * Nome exigido pelo enunciado.
 */
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    // TODO: alertas mais recentes primeiro (nomes batem com os atributos de Alerta):
    //       List<Alerta> findAllByOrderByIssuedAtDesc();

    // TODO (opcional): filtrar por nível:
    //       List<Alerta> findByLevel(NivelAlerta level);
}
