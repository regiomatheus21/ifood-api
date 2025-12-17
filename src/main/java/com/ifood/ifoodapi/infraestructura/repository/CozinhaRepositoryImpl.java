package com.ifood.ifoodapi.infraestructura.repository;

import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class,id);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Optional<Cozinha> cozinha = Optional.ofNullable(buscar(id));
        if(cozinha.isPresent()){
            manager.remove(cozinha.get());
        }
    }
}
