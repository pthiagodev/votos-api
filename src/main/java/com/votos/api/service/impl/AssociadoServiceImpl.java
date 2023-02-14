package com.votos.api.service.impl;

import com.votos.api.dto.AtualizaAssociadoDTO;
import com.votos.api.model.Associado;
import com.votos.api.repository.AssociadoRepository;
import com.votos.api.service.AssociadoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    AssociadoRepository repository;

    @Override
    public Associado buscar(Long id) throws NoSuchElementException {
        Optional<Associado> opt = repository.findById(id);
        return opt.get();
    }

    @Override
    public List<Associado> listar() {
        return repository.findAll();
    }

    @Override
    public Associado salvar(Associado associado) {
        return repository.save(associado);
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
    public void atualizar(AtualizaAssociadoDTO dados) {
        var associado = repository.getReferenceById(dados.id());
        if (associado != null)
            associado.atualizaAssociado(dados);
        throw new EntityNotFoundException();
    }
}
