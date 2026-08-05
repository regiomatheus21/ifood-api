package com.ifood.ifoodapi.api.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

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

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,HttpHeaders headers,
                HttpStatus status, WebRequest request){
      //Percorre toda a pilha de exception e pega a causa raiz
       Throwable  rootCause = ExceptionUtils.getRootCause(ex);
       if(rootCause instanceof InvalidFormatException){
           return handleInvalidFormatException((InvalidFormatException) rootCause,headers,status,request);
       }

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisicao está invalido. Verifique erro de sintaxe.";
        Problem problem = createProblemBuilder(status,problemType,detail).build();
        return handleExceptionInternal(ex,problem,headers,status,request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
         return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s' " +
                "que é de um tipo inválido. Corrija e informe um valor compátivel com o tipo %s.",path,ex.getValue(),ex.getTargetType().getSimpleName());
        Problem problem = createProblemBuilder(status,problemType,detail).build();
        return handleExceptionInternal(ex,problem,headers,status,request);
    }


}
