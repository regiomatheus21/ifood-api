package com.ifood.ifoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonRootName("cozinha")
public class Cozinha {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cozinha")
    @JsonIgnore //Deixa esse metodo só para fim de estudo mesmo mais nao e necessario ele.
    //o ignore e para que nao fique em loop quando for serelizar o json nao ficar dando loop no restaurante e cozinha.
    private List<Restaurante> restaurantes = new ArrayList<>();
}
