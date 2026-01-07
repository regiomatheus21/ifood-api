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
    public Restaurante adicionar(Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cozinha nao foi encontrada com esse id: %d",restaurante.getCozinha().getId())));
                restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }
    public void deletar(Long restauranteId) {
        Restaurante rest =restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Restaurante nao encontrado")));
            restauranteRepository.delete(rest);
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }
    public Optional<Restaurante> buscar(Long restauranteId) {
       return restauranteRepository.findById(restauranteId);
    }
}
