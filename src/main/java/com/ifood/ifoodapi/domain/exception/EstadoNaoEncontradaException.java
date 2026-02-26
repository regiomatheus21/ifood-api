package com.ifood.ifoodapi.domain.exception;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static long serialVersionUID= 1L;

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de estado com código %d";
    public EstadoNaoEncontradaException(String mensagem){
        super(mensagem);
    }
    public EstadoNaoEncontradaException(Long id){
        this(String.format(MSG_ESTADO_NAO_ENCONTRADO,id));
    }
}
