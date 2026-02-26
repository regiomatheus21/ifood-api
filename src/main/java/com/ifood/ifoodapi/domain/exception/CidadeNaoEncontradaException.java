package com.ifood.ifoodapi.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static long serialVersionUID= 1L;

    public static final String MSG_CIDADE_NAO_ENCONTRADO = "Não existe um cadastro de cidade com código %d";
    public CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
    public CidadeNaoEncontradaException(Long id){
        this(String.format(MSG_CIDADE_NAO_ENCONTRADO,id));
    }




}
