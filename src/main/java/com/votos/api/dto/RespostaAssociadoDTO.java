package com.votos.api.dto;

import com.votos.api.model.Associado;

public record RespostaAssociadoDTO(Long id, String name) {
    public RespostaAssociadoDTO(Associado associado) {
        this(associado.getId(), associado.getNome());
    }
}
