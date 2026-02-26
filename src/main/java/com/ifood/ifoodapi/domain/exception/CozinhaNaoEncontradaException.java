package com.ifood.ifoodapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static long serialVersionUID= 1L;

    public static final String MSG_COZINHA_NAO_ENCONTRADO = "Não existe um cadastro de cozinha com código %d";
    public CozinhaNaoEncontradaException(String mensagem){
        super(mensagem);
    }
    public CozinhaNaoEncontradaException(Long id){
        this(String.format(MSG_COZINHA_NAO_ENCONTRADO,id));
    }




}
