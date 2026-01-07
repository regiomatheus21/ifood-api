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
        Optional<Estado> estado =estadoRepository.findById(estadoId);
        if(!estado.isPresent()){
            throw new EntidadeNaoEncontradaException(
                    String.format("Nao existe cadastro de estado para esse id %d",estadoId));
        }
        cidade.setEstado(estado.get());
        return cidadeRepository.save(cidade);
    }

    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.getReferenceById(cidadeId);
    }

    public void excluir(Long cidadeId) {
       Optional<Cidade> cidade = Optional.of(cidadeRepository.getReferenceById(cidadeId));
        if (cidade.isPresent()){
            cidadeRepository.delete(cidade.get());
        }
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

//    public Cidade atualizar(Long cidadeId, Cidade cidade) {
//        Optional<Cidade> cidadeAtual = Optional.ofNullable(cidadeRepository.buscar(cidadeId));
//        if(cidadeAtual.isPresent()){
//           BeanUtils.copyProperties(cidade,cidadeAtual,"id");
//            Cidade cidadeAtualizada = cidadeRepository.salvar(cidadeAtual.get());
//            return cidadeAtualizada;
//
//        }
//    }
}
