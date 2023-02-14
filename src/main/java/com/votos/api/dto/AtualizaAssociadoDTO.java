package com.votos.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizaAssociadoDTO(@NotNull Long id, String nome, String cpf) {
}
