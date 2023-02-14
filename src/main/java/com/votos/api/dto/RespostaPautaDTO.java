package com.votos.api.dto;

import com.votos.api.model.Pauta;

public record RespostaPautaDTO(Long id, String tema) {
    public RespostaPautaDTO(Pauta pauta) {
        this(pauta.getId(), pauta.getTema());
    }
}
