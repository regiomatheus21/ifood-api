package com.ifood.ifoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException{
    private static long serialVersionUID= 1L;

    public EntidadeEmUsoException(String mensagem){
        super(mensagem);
    }
}
