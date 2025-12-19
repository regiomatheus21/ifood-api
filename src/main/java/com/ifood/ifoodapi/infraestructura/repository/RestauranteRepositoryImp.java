package com.ifood.ifoodapi.infraestructura.repository;

import com.ifood.ifoodapi.domain.model.Restaurante;
import com.ifood.ifoodapi.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteRepositoryImp implements RestauranteRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Override
    public List<Restaurante> listar() {
        return manager.createQuery("from Restaurante",Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return manager.find(Restaurante.class,id);
    }

    @Override
    public void remover(Long id) {
        manager.remove(id);
    }
}
