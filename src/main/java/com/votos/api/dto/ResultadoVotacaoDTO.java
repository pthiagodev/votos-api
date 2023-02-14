package com.votos.api.dto;

import com.votos.api.utils.Resultado;

public record ResultadoVotacaoDTO(String tema, Resultado resultado, Long sim, Long nao) {
}
