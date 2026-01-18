package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long> {

}
