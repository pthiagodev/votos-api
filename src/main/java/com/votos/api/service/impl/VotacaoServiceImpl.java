package com.votos.api.service.impl;

import com.votos.api.dto.AtualizaVotacaoDTO;
import com.votos.api.model.Pauta;
import com.votos.api.model.Votacao;
import com.votos.api.repository.VotacaoRepository;
import com.votos.api.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VotacaoServiceImpl implements VotacaoService {

    @Autowired
    private VotacaoRepository repository;

    @Override
    public List<Votacao> listar() {
        return repository.findAll();
    }

    @Override
    public Votacao buscar(Long id) {
        Optional<Votacao> opt = repository.findById(id);
        return opt.get();
    }

    @Override
    public Votacao salvar(Pauta pauta, ZonedDateTime dataFinal) {
        if (dataFinal == null)
            dataFinal = ZonedDateTime.now().plusMinutes(1);
        return repository.save(new Votacao(pauta, dataFinal));
    }

    @Override
    public void deletar(Long id) {
    if (repository.existsById(id))
        repository.deleteById(id);
    }

    @Override
    public void atualizar(AtualizaVotacaoDTO dados) {
        var votacao = repository.getReferenceById(dados.id());
        votacao.atualizaVotacao(dados);
    }
}
