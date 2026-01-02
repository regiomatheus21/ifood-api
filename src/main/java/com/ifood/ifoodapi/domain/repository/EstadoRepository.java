package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    Estado salvar(Estado estado);
    List<Estado> listar();
    Estado buscar (Long id);
    void remover (Long id);
}
