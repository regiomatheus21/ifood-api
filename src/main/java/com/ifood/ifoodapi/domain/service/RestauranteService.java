package com.ifood.ifoodapi.domain.service;

import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.model.Restaurante;
import com.ifood.ifoodapi.domain.repository.CozinhaRepository;
import com.ifood.ifoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;
    public void adicionar(Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());
        if(cozinha!=null){
            restaurante.setCozinha(cozinha);
        }else{
            throw new EntidadeNaoEncontradaException(String.format("Cozinha nao foi encontrada com esse id: %d",restaurante.getCozinha().getId()));
        }
        restauranteRepository.salvar(restaurante);
    }

    public void deletar(Long restauranteId) {
        Optional<Restaurante> rest = Optional.ofNullable( restauranteRepository.buscar(restauranteId));
        if(rest.isPresent()){
            restauranteRepository.remover(restauranteId);
        }
    }

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }
}
