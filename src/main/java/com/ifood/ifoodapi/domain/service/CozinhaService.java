package com.ifood.ifoodapi.domain.service;

import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public void adicionar(Cozinha cozinha) {
        cozinhaRepository.salvar(cozinha);
    }

    public void excluir(Long cozinhaId){
        try {
            cozinhaRepository.remover(cozinhaId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha para esse id"));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cozinha nao pode ser removida, esta em uso"));
        }
    }

    public Cozinha getById(Long cozinhaId) {
        return cozinhaRepository.buscar(cozinhaId);
    }

    public List<Cozinha> get() {
        return cozinhaRepository.listar();
    }
}
