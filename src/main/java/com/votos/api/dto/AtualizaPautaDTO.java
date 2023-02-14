package com.votos.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizaPautaDTO(@NotNull Long id, String tema) {
}
