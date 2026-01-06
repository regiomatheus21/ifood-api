package com.ifood.ifoodapi.domain.repository;

import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
