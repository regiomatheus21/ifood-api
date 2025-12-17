package com.ifood.ifoodapi.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    private static long serialVersionUID= 1L;
    public EntidadeNaoEncontradaException (String mensagem){
        super(mensagem);
    }
}
