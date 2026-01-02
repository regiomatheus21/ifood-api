package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    Restaurante salvar(Restaurante restaurante);
    List<Restaurante> listar();
    Restaurante buscar (Long id);
    void remover (Long id);
}
