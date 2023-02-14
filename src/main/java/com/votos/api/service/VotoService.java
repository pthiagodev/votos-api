package com.votos.api.service;

import com.votos.api.exception.VotacaoAbertaException;
import com.votos.api.model.Associado;
import com.votos.api.model.Pauta;
import com.votos.api.model.Votacao;
import com.votos.api.model.Voto;
import com.votos.api.utils.Resultado;

import java.time.ZonedDateTime;
import java.util.List;

public interface VotoService {

    public List<Voto> listarPelaVotacao(Long idVotacao);

    public Long contaQtdDeVotos(boolean favoravel, Long idVotacao);

    public Voto buscar(Long id);

    public Voto salvar(Voto voto, Votacao votacao, Associado associado) throws Exception;

    public void deletar(Long id);

    public Resultado calculaResultado(Votacao votacao) throws VotacaoAbertaException;
}
