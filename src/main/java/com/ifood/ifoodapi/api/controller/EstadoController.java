package com.ifood.ifoodapi.api.controller;

import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.model.Estado;
import com.ifood.ifoodapi.domain.service.EstadoService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar(){
        return estadoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar (@RequestBody Estado estado){
        return estadoService.salvar(estado);
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar (@PathVariable Long estadoId){
        Optional<Estado> estado= estadoService.buscar(estadoId);
        if(estado.isPresent()){
            return ResponseEntity.ok().body(estado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        try {
            Optional<Estado> estadoAtual = estadoService.buscar(estadoId);
            if(estadoAtual.isPresent()){
                BeanUtils.copyProperties(estado,estadoAtual.get(),"id");
                Estado estadoAtualizado = estadoService.salvar(estadoAtual.get());
                return ResponseEntity.ok().body(estadoAtualizado);
            }
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId){
        try {
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
