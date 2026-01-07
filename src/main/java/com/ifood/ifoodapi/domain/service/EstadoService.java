package com.ifood.ifoodapi.domain.service;

import com.ifood.ifoodapi.domain.exception.EntidadeEmUsoException;
import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Estado;
import com.ifood.ifoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
       return estadoRepository.findAll();
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }
    public void excluir(Long estadoId) {
        try {
            Optional<Estado> estado= estadoRepository.findById(estadoId);
            if(estado.isPresent()){
                estadoRepository.delete(estado.get());
            }
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
        }
    }

    public Optional<Estado> buscar(Long estadoId) {
        return estadoRepository.findById(estadoId);
    }
}
