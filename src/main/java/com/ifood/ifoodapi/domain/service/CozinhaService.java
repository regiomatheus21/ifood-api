package com.ifood.ifoodapi.domain.service;

import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
       return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId){
        try {
            Cozinha cozinha = cozinhaRepository.getReferenceById(cozinhaId);
            cozinhaRepository.delete(cozinha);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha para esse id"));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cozinha nao pode ser removida, esta em uso"));
        }
    }

    public Optional<Cozinha> buscar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId);
    }

    public List<Cozinha> get() {
        return cozinhaRepository.findAll();
    }
}
