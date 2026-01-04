package com.ifood.ifoodapi.api.controller;

import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeService.listar();
    }

    @PostMapping
    public ResponseEntity<?> adicionar (@RequestBody Cidade cidade){
        try {
            cidade = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar (@PathVariable Long cidadeId){
        Cidade cidade= cidadeService.buscar(cidadeId);
       if(cidade != null){
           return ResponseEntity.ok().body(cidade);
       }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
        try {
            Cidade cidadeAtual = cidadeService.buscar(cidadeId);
            if(cidadeAtual != null){
                BeanUtils.copyProperties(cidade,cidadeAtual,"id");
                cidadeAtual = cidadeService.salvar(cidadeAtual);
                return ResponseEntity.ok().body(cidadeAtual);
            }
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId){
        try {
            cidadeService.excluir(cidadeId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
