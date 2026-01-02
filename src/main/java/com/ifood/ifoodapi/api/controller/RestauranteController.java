package com.ifood.ifoodapi.api.controller;

import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Restaurante;
import com.ifood.ifoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try {
            restauranteService.adicionar(restaurante);
            return ResponseEntity.ok().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public List<Restaurante> listar(){
        return restauranteService.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        if(restaurante!=null){
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        try {
            Restaurante restauranteAtual = restauranteService.buscar(restauranteId);
            if(restauranteAtual!=null){
                BeanUtils.copyProperties(restaurante,restauranteAtual,"id");
                restauranteAtual = restauranteService.adicionar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }
            return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradaException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
