package com.ifood.ifoodapi.infraestructura.repository;

import com.ifood.ifoodapi.domain.model.Estado;
import com.ifood.ifoodapi.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
    @PersistenceContext
    private EntityManager maneger;

    @Override
    @Transactional
    public Estado salvar(Estado estado) {
        return maneger.merge(estado);
    }

    @Override
    public List<Estado> listar() {
        return maneger.createQuery("From Estado",Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return maneger.find(Estado.class,id);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        maneger.remove(id);

    }
}
