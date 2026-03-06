package com.ifood.ifoodapi.domain.exception;

/*
    - Essa classe nao pode mais ser instaciada.
    - A funcao dela sera para que se voce quiser algo mais generico no servico voce pode fazer um cast dela.
 */

public abstract class EntidadeNaoEncontradaException extends RuntimeException{
    private static long serialVersionUID= 1L;
    public EntidadeNaoEncontradaException (String mensagem){
        super(mensagem);
    }
}
