package com.ifood.ifoodapi.domain.exception;

public class EntidadeEmUsoException extends RuntimeException{
    private static long serialVersionUID= 1L;

    public EntidadeEmUsoException(String mensagem){
        super(mensagem);
    }
}
