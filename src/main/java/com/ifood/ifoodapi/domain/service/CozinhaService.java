package com.ifood.ifoodapi.domain.service;

import com.ifood.ifoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cozinha;
import com.ifood.ifoodapi.domain.repository.CozinhaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {
    public static final String MSG_CADASTRO_COZINHA_NAO_ENCONTRADO = "Não existe um cadastro de cozinha para esse id";
    public static final String MSG_COZINHA_NAO_ENCONTRADO = "Cozinha nao encontrada";
    public static final String MSG_DELETE_COZINHA = "Cozinha nao pode ser removida, esta em uso";
    public static final String COZINHA_EM_USO = "Cozinha em uso";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        if(cozinhaRepository.existsByNome(cozinha.getNome())){
            throw new EntidadeEmUsoException(COZINHA_EM_USO);
        }
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId){
        try {
            Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
            cozinhaRepository.delete(cozinha.get());
        }catch (EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradaException(cozinhaId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(MSG_DELETE_COZINHA));
        }
    }

    public Cozinha buscar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }

    public List<Cozinha> get() {
        return cozinhaRepository.findAll();
    }
}
