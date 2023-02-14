package com.votos.api.service.impl;

import com.votos.api.exception.VotacaoAbertaException;
import com.votos.api.exception.VotacaoEncerradaException;
import com.votos.api.exception.VotoDuplicadoException;
import com.votos.api.model.Associado;
import com.votos.api.model.Votacao;
import com.votos.api.model.Voto;
import com.votos.api.repository.VotoRepository;
import com.votos.api.service.VotoService;
import com.votos.api.utils.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository repository;

    @Override
    public List<Voto> listarPelaVotacao(Long idVotacao) {
        return repository.getByVotacaoId(idVotacao);
    }

    @Override
    public Long contaQtdDeVotos(boolean favoravel, Long idVotacao) {
        return repository.countByFavoravelAndVotacaoId(favoravel, idVotacao);
    }

    @Override
    public Voto buscar(Long id) {
        Optional<Voto> opt = repository.findById(id);
        return opt.get();
    }

    @Override
    public Voto salvar(Voto voto, Votacao votacao, Associado associado) throws Exception {

        voto.setDataRegistro(ZonedDateTime.now());
        voto.setVotacao(votacao);
        voto.setAssociado(associado);

        if (repository.findVotoByAssociadoAndVotacao(associado, votacao) != null)
            throw new VotoDuplicadoException("Associado já registrou voto!");

        if (voto.getDataRegistro().isAfter(voto.getVotacao().getDataFinal()))
            throw new VotacaoEncerradaException("Votação já foi encerrada!");

        return repository.save(voto);
    }

    @Override
    public void deletar(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

    @Override
    public Resultado calculaResultado(Votacao votacao) throws VotacaoAbertaException {

        if (votacao.getDataFinal().isAfter(ZonedDateTime.now()))
            throw new VotacaoAbertaException("A votação ainda está aberta!");

        var sim = this.contaQtdDeVotos(true, votacao.getId());
        var nao = this.contaQtdDeVotos(false, votacao.getId());

        if (sim > nao)
            return Resultado.APROVADO;
        if (sim == nao)
            return Resultado.EMPATADO;
        return Resultado.REJEITADO;
    }
}
