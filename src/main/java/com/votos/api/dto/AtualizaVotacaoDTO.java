package com.votos.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public record AtualizaVotacaoDTO(@NotNull Long id, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime dataFinal) {

}
