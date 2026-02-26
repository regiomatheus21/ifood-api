package com.ifood.ifoodapi.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static long serialVersionUID= 1L;

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";
    public RestauranteNaoEncontradaException(String mensagem){
        super(mensagem);
    }
    public RestauranteNaoEncontradaException(Long id){
        this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO,id));
    }
}
