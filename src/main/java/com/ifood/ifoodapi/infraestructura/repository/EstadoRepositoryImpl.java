package com.ifood.ifoodapi.infraestructura.repository;

import com.ifood.ifoodapi.domain.model.Estado;
import com.ifood.ifoodapi.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class EstadoRepositoryImpl implements EstadoRepository {
    @PersistenceContext
    private EntityManager maneger;

    @Override
    public Estado salvar(Estado estado) {
        return null;
    }

    @Override
    public List<Estado> listar() {
        return List.of();
    }

    @Override
    public Estado buscar(Long id) {
        return maneger.find(Estado.class,id);
    }

    @Override
    public void remover(Long id) {

    }
}
