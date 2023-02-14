package com.votos.api.controller;

import com.votos.api.dto.AtualizaPautaDTO;
import com.votos.api.dto.CadastroPautaDTO;
import com.votos.api.dto.RespostaPautaDTO;
import com.votos.api.model.Pauta;
import com.votos.api.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    @Autowired private PautaService service;

    @GetMapping
    public ResponseEntity<Stream<RespostaPautaDTO>> listar() {
        var listaDTO = service.listar().stream().map(RespostaPautaDTO::new);
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        return ResponseEntity.ok(new RespostaPautaDTO(service.buscar(id)));
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroPautaDTO dados, UriComponentsBuilder uriBuilder) {
        var pauta = new Pauta(dados);
        service.salvar(pauta);
        var uri = uriBuilder.path("/pautas/{id}").buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uri).body(new RespostaPautaDTO(pauta));
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody @Valid AtualizaPautaDTO dados) {
        service.atualizar(dados);
        var pauta = service.buscar(dados.id());
        return ResponseEntity.ok(new RespostaPautaDTO(pauta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
