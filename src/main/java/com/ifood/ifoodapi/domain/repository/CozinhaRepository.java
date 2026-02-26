package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {
    boolean existsByNome(String nome);

}
