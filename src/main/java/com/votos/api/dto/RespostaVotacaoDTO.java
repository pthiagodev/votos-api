package com.votos.api.dto;

import com.votos.api.model.Votacao;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public record RespostaVotacaoDTO(Long id, String tema, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime dataFinal) {
    public RespostaVotacaoDTO(Votacao votacao){
        this(votacao.getId(), votacao.getPauta().getTema(), votacao.getDataFinal());
    }
}
