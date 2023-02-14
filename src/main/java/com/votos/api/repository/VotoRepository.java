package com.votos.api.repository;

import com.votos.api.model.Associado;
import com.votos.api.model.Votacao;
import com.votos.api.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> getByVotacaoId(Long idVotacao);
    Voto findVotoByAssociadoAndVotacao(Associado associado, Votacao votacao);
    Long countByFavoravelAndVotacaoId(boolean favoravel, Long idVotacao);
}
