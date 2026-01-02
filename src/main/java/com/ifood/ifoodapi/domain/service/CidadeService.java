package com.ifood.ifoodapi.domain.service;

import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.model.Estado;
import com.ifood.ifoodapi.domain.repository.CidadeRepository;
import com.ifood.ifoodapi.domain.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    private EstadoRepository estadoRepository;
    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(estadoId);

        if(estado == null){
            throw new EntidadeNaoEncontradaException(
                    String.format("Nao existe cadastro de estado para esse id %d",estadoId));
        }
        cidade.setEstado(estado);
        return cidadeRepository.salvar(cidade);
    }
}
