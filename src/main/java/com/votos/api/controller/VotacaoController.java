package com.votos.api.controller;

import com.votos.api.dto.*;
import com.votos.api.exception.PautaJaPossuiVotacaoException;
import com.votos.api.exception.PautaNaoCadastradaException;
import com.votos.api.exception.VotacaoAbertaException;
import com.votos.api.model.Votacao;
import com.votos.api.model.Voto;
import com.votos.api.service.AssociadoService;
import com.votos.api.service.PautaService;
import com.votos.api.service.VotacaoService;
import com.votos.api.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.stream.Stream;

@RestController
@RequestMapping("/votacoes")
public class VotacaoController {

    @Autowired private VotacaoService votacaoService;
    @Autowired private PautaService pautaService;
    @Autowired private VotoService votoService;
    @Autowired private AssociadoService associadoService;

    @GetMapping
    public ResponseEntity<Stream<RespostaVotacaoDTO>> listarVotacoes() {
        var listaDTO = votacaoService.listar().stream().map(RespostaVotacaoDTO::new);
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarVotacao(@PathVariable Long id) {
        return ResponseEntity.ok(new RespostaVotacaoDTO(votacaoService.buscar(id)));
    }

    @PostMapping
    public Votacao cadastrarVotacao(@RequestBody CadastroVotacaoDTO dados) throws PautaNaoCadastradaException, PautaJaPossuiVotacaoException {
        var pauta = pautaService.buscar(dados.idPauta());
        if (pauta == null)
            throw new PautaNaoCadastradaException("Pauta não foi cadastrada!");
        if (pauta.getVotacao() != null)
            throw new PautaJaPossuiVotacaoException("Pauta já possui votação!");

        Votacao votacao = votacaoService.salvar(pauta, dados.dataFinal());
        pautaService.vincularVotacao(votacao);
        return votacao;
    }

    @PutMapping
    public ResponseEntity atualizarVotacao(@RequestBody @Valid AtualizaVotacaoDTO dados) {
        votacaoService.atualizar(dados);
        var votacao = votacaoService.buscar(dados.id());
        return ResponseEntity.ok(new RespostaVotacaoDTO(votacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarVotacao(@PathVariable Long id) {
        pautaService.desvincularVotacao(votacaoService.buscar(id));
        votacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/votos")
    public ResponseEntity<Stream<RespostaVotoDTO>> listarVotosPelaVotacao(@PathVariable Long id) {
        var listaDTO = votoService.listarPelaVotacao(id).stream().map(RespostaVotoDTO::new);
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/votos/{id}")
    public ResponseEntity buscarVoto(@PathVariable Long id) {
        return ResponseEntity.ok(new RespostaVotoDTO(votoService.buscar(id)));
    }

    @PostMapping("/{id}/votos")
    public ResponseEntity cadastrarVoto(@PathVariable Long id, @RequestBody CadastroVotoDTO dados, UriComponentsBuilder uriBuilder) throws Exception {
        var votacao = votacaoService.buscar(id);
        var voto = new Voto(dados.favoravel());
        var associado = associadoService.buscar(dados.idAssociado());
        voto = votoService.salvar(voto, votacao, associado);

        var uri = uriBuilder.path("/votos/{id}").buildAndExpand(voto.getId()).toUri();
        return ResponseEntity.created(uri).body(new RespostaVotoDTO(voto));
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity resultadoVotacao(@PathVariable Long id) throws VotacaoAbertaException {
        var votacao = votacaoService.buscar(id);
        var resultado = votoService.calculaResultado(votacao);
        var sim = votoService.contaQtdDeVotos(true, id);
        var nao = votoService.contaQtdDeVotos(false, id);

        return ResponseEntity.ok(new ResultadoVotacaoDTO(votacao.getPauta().getTema(), resultado, sim, nao));
    }
}
