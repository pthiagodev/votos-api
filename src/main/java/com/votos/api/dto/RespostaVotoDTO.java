package com.votos.api.dto;

import com.votos.api.model.Voto;

public record RespostaVotoDTO(Long id, String tema, String nome, boolean favoravel) {
    public RespostaVotoDTO(Voto voto) {
        this(voto.getId(), voto.getVotacao().getPauta().getTema(), voto.getAssociado().getNome(), voto.getFavoravel());
    }
}
