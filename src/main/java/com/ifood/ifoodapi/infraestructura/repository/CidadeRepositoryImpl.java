package com.ifood.ifoodapi.infraestructura.repository;

import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    public List<Cidade> listar() {
        return List.of();
    }

    @Override
    public Cidade buscar(Long id) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
