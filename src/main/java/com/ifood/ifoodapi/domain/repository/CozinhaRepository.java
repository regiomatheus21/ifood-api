package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    Cozinha salvar(Cozinha cozinha);
    List<Cozinha> listar();
    Cozinha buscar (Long id);
    void remover (Long id);
}
