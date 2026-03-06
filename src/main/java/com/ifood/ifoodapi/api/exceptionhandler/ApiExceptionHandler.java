package com.ifood.ifoodapi.api.exceptionhandler;

import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
    - ControllerAdvice - Dentro desse componente podemos adicionar excepions handlers, aonde excecoes de outros
        controladores do nosso projeto serao tratadas por aqui.
        -Assim é colocado um ponto central de todas as excecoes do projeto.
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
       return handleExceptionInternal(ex,ex.getMessage(),new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex ,WebRequest request){
        return handleExceptionInternal(ex,ex.getMessage(),new HttpHeaders(),HttpStatus.CONFLICT,request);
    }


}
