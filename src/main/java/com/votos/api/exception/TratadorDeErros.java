package com.votos.api.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler({NoSuchElementException.class, EntityNotFoundException.class})
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity tratarErro400(ConstraintViolationException ex) {
        var erros = ex.getConstraintViolations();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(VotoDuplicadoException.class)
    public ResponseEntity tratarErroVotoInvalido(VotoDuplicadoException ex) {
        var erro = ex.getMensagem();
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(VotacaoEncerradaException.class)
    public ResponseEntity tratarErroVotacaoEncerrada(VotacaoEncerradaException ex) {
        var erro = ex.getMensagem();
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(VotacaoAbertaException.class)
    public ResponseEntity tratarErroVotacaoAberta(VotacaoAbertaException ex) {
        var erro = ex.getMensagem();
        return ResponseEntity.badRequest().body(erro);
    }

    public record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(ConstraintViolation cv) {
            this(cv.getPropertyPath().toString(), cv.getMessage());
        }
    }
}
