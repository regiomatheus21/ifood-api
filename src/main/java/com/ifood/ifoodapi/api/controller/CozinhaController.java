package com.ifood.ifoodapi.api.controller;

import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("v1/cozinha")
public class CozinhaController {
    /*
        - O @ResquestBody ele transforma os dados que informamos no momento da requisicao no json para o objeto especificado.
        Como por exemplo cozinha, podia ser o cozinhaDTO.
        - Na requisiçao o content-type significa o formato dos dados que esta sendo enviado para o servidor (XML ou JSON)
        - POST - CRIAR ALGO/ ADICIONA ALGO.
        - PUT  - ALTERA COMPLETAMENTE UM RECURSO.
        - PATCH- ALTERA PARCIALMENTE UM RECURSO.
        - DELETE-VOCE EXCLUI UM RECURSO.
        - @PathVariable sao variaveis que vem na URL. ex: /users/123 o pathVariable e o 123.
     */
    @Autowired
    private CozinhaService cozinhaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Cozinha cozinha){
        cozinhaService.adicionar(cozinha);
    }
    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
        try {
            cozinhaService.excluir(cozinhaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
        catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> getById(@PathVariable Long cozinhaId){
            Cozinha cozinha = cozinhaService.getById(cozinhaId);
            if(cozinha != null){
                return ResponseEntity.ok(cozinha);
            }
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Cozinha>> get(){
        List<Cozinha> cozinhas = cozinhaService.get();
        if(cozinhas != null){
            return ResponseEntity.ok(cozinhas);
        }
        return ResponseEntity.notFound().build();
    }

}
