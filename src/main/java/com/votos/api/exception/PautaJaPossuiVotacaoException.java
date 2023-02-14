package com.votos.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaJaPossuiVotacaoException extends Throwable {
    private String mensagem;

    public PautaJaPossuiVotacaoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
