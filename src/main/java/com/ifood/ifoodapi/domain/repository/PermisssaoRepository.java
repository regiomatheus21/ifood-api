package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisssaoRepository extends JpaRepository<Permissao,Long> {
}
