package com.votos.api.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public record CadastroVotacaoDTO(Long idPauta, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime dataFinal) {
}
