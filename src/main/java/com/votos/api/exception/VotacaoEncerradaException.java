package com.votos.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotacaoEncerradaException extends Exception {
    private String mensagem;

    public VotacaoEncerradaException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
