package com.votos.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaNaoCadastradaException extends Throwable {
    private String mensagem;

    public PautaNaoCadastradaException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
