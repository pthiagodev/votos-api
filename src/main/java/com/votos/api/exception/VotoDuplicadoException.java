package com.votos.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoDuplicadoException extends Exception {
    private String mensagem;

    public VotoDuplicadoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
