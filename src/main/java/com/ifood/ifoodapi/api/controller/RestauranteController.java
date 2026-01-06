package com.ifood.ifoodapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Restaurante;
import com.ifood.ifoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String , Object> campos){
        Restaurante restauranteAtual = restauranteService.buscar(restauranteId);
        if(restauranteAtual == null){
            return ResponseEntity.notFound().build();
        }
        merge(campos,restauranteAtual);
        return atualizar(restauranteId,restauranteAtual);

    }
    private void merge(Map<String, Object> camposOrigem,Restaurante restauranteDestino) {
        camposOrigem.forEach((nomePropriedade, valorPropriedade)-> {
            ObjectMapper objectMapper= new ObjectMapper();
            Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem,Restaurante.class);
            Field field = ReflectionUtils.findField(Restaurante.class,nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field,restauranteOrigem);
            ReflectionUtils.setField(field,restauranteDestino,novoValor);
        });
    }
}
