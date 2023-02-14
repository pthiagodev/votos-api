package com.votos.api.service;

import com.votos.api.dto.AtualizaVotacaoDTO;
import com.votos.api.model.Pauta;
import com.votos.api.model.Votacao;

import java.time.ZonedDateTime;
import java.util.List;

public interface VotacaoService {

    public List<Votacao> listar();

    public Votacao buscar(Long id);

    public Votacao salvar(Pauta pauta, ZonedDateTime dataFinal);

    public void deletar(Long id);

    public void atualizar(AtualizaVotacaoDTO dados);
}
