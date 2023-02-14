package com.votos.api.service;

import com.votos.api.dto.AtualizaPautaDTO;
import com.votos.api.model.Pauta;
import com.votos.api.model.Votacao;

import java.util.List;

public interface PautaService {

    public Pauta buscar(Long id);

    public List<Pauta> listar();

    public Pauta salvar(Pauta pauta);

    public void deletar(Long id);

    public Pauta vincularVotacao(Votacao votacao);

    public void desvincularVotacao(Votacao votacao);

    public void atualizar(AtualizaPautaDTO dados);
}
