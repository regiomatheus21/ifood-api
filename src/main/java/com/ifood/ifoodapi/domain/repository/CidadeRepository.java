package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.model.Cozinha;

import java.util.List;

public interface CidadeRepository {
    Cidade salvar(Cidade cidade);
    List<Cidade> listar();
    Cidade buscar (Long id);
    void remover (Long id);
}
