package com.ifood.ifoodapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false)
    private String nome;

    /*
        - Um grupo ele pode ter varias permissoes
        - Uma permissao pode ter varios grupos por isso ManyToMany
        - o joinTable ele criar uma tabela intermediaria chamada grupo_permissao nessa tabela que ficara
        - Registrado as permissoes dos grupos.
     */
    @ManyToMany
    @JoinTable(name = "grupo_permissao",joinColumns = @JoinColumn(name = "grupo_id"),
        inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<Permissao> permissaos = new ArrayList<>();



}
