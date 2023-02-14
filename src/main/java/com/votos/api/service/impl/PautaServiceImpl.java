package com.votos.api.service.impl;

import com.votos.api.dto.AtualizaPautaDTO;
import com.votos.api.model.Pauta;
import com.votos.api.model.Votacao;
import com.votos.api.repository.PautaRepository;
import com.votos.api.service.PautaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository repository;

    @Override
    public Pauta buscar(Long id) throws NoSuchElementException {
        if (id == null)
            throw new NoSuchElementException();
        Optional<Pauta> opt = repository.findById(id);
        return opt.get();
    }

    @Override
    public List<Pauta> listar() {
        return repository.findAll();
    }

    @Override
    public Pauta salvar(Pauta pauta) {
        return repository.save(pauta);
    }

    @Override
    public void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }

    }

    @Override
    public Pauta vincularVotacao(Votacao votacao) {
        var pauta = repository.getReferenceById(votacao.getPauta().getId());
        pauta.vinculaVotacao(votacao);
        return pauta;
    }

    @Override
    public void desvincularVotacao(Votacao votacao) {
        var pauta = repository.getReferenceById(votacao.getPauta().getId());
        pauta.desvinculaVotacao();
    }

    @Override
    public void atualizar(AtualizaPautaDTO dados) {
        var pauta = repository.getReferenceById(dados.id());
        if (pauta != null)
            pauta.atualizaPauta(dados);
        throw new EntityNotFoundException();
    }

}
