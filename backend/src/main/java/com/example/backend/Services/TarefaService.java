package com.example.backend.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.backend.Entities.Tarefa;
import com.example.backend.Enums.Prioridade;
import com.example.backend.Enums.Situacao;
import com.example.backend.Repositories.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> listarComCursor(Long cursor, int limit, String nome, Prioridade prioridade, Situacao situacao) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("id").ascending());
        return repository.findNextPage(cursor, nome, prioridade, situacao, pageable);
    }

    public Tarefa salvar(Tarefa tarefa) {
        tarefa.setSituacao(Situacao.ABERTA);
        tarefa.setDataCriacao(OffsetDateTime.now());

        if (tarefa.getDataPrevistaConclusao().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data prevista de conclusão não pode ser anterior a hoje.");
        }

        return repository.save(tarefa);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Tarefa concluir(Long id) {
        Tarefa tarefa = repository.findById(id).orElseThrow();
        tarefa.setSituacao(Situacao.CONCLUIDA);
        return repository.save(tarefa);
    }

    public Tarefa pendente(Long id) {
        Tarefa tarefa = repository.findById(id).orElseThrow();
        tarefa.setSituacao(Situacao.PENDENTE);
        return repository.save(tarefa);
    }
}