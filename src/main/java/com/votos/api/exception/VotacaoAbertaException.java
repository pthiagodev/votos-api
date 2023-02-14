package com.votos.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotacaoAbertaException extends Exception {
    private String mensagem;

    public VotacaoAbertaException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
