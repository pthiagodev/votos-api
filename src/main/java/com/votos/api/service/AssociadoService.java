package com.votos.api.service;

import com.votos.api.dto.AtualizaAssociadoDTO;
import com.votos.api.model.Associado;

import java.util.List;

public interface AssociadoService {

    public Associado buscar(Long id);

    public List<Associado> listar();

    public Associado salvar(Associado associado);

    public void deletar(Long id);

    public void atualizar(AtualizaAssociadoDTO dados);
}
