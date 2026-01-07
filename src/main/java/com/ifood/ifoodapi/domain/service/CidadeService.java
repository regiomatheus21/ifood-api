package com.ifood.ifoodapi.domain.service;

import com.ifood.ifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.ifood.ifoodapi.domain.model.Cidade;
import com.ifood.ifoodapi.domain.model.Estado;
import com.ifood.ifoodapi.domain.repository.CidadeRepository;
import com.ifood.ifoodapi.domain.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado =estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de estado para esse id %d",estadoId)));
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }
    public Optional<Cidade> buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId);
    }
    public void excluir(Long cidadeId) {
       Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
        if (cidade.isPresent()){
            cidadeRepository.delete(cidade.get());
        }
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }
}
