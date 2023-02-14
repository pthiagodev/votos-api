package com.votos.api.controller;

import com.votos.api.dto.AtualizaAssociadoDTO;
import com.votos.api.dto.RespostaAssociadoDTO;
import com.votos.api.model.Associado;
import com.votos.api.dto.CadastroAssociadoDTO;
import com.votos.api.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService service;

    @GetMapping
    public ResponseEntity<Stream<RespostaAssociadoDTO>> listar() {
        var listaDTO = service.listar().stream().map(RespostaAssociadoDTO::new);
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        return ResponseEntity.ok(new RespostaAssociadoDTO(service.buscar(id)));
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroAssociadoDTO dados, UriComponentsBuilder uriBuilder) {
        var associado = new Associado(dados);
        service.salvar(associado);

        var uri = uriBuilder.path("/associados/{id}").buildAndExpand(associado.getId()).toUri();
        return ResponseEntity.created(uri).body(new RespostaAssociadoDTO(associado));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid AtualizaAssociadoDTO dados) {
        service.atualizar(dados);
        var associado = service.buscar(dados.id());

        return ResponseEntity.ok(new RespostaAssociadoDTO(associado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
