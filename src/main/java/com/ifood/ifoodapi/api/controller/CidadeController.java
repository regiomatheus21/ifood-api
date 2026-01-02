package com.ifood.ifoodapi.api.controller;

import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @PostMapping
    public ResponseEntity<?> adicionar (@RequestBody Cidade cidade){
        try {
            cidade = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
