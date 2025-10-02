package com.example.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.backend.Entities.Tarefa;
import com.example.backend.Enums.Prioridade;
import com.example.backend.Enums.Situacao;
import com.example.backend.Repositories.TarefaRepository;

@DataJpaTest
class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        for (int i = 1; i <= 5; i++) {
            Tarefa t = new Tarefa();
            t.setNome("Tarefa " + i);
            t.setPrioridade(Prioridade.BAIXA);
            t.setSituacao(Situacao.ABERTA);
            t.setDataPrevistaConclusao(LocalDate.now().plusDays(i));
            t.setDataCriacao(OffsetDateTime.now());
            repository.save(t);
        }
    }

    @Test
    void deveListarComCursor() {
        List<Tarefa> primeiroLote = repository.findNextPage(null, null, null, null, PageRequest.of(0, 2));
        assertEquals(2, primeiroLote.size());

        Long ultimoId = primeiroLote.get(1).getId();
        List<Tarefa> segundoLote = repository.findNextPage(ultimoId, null, null, null, PageRequest.of(0, 2));
        assertEquals(2, segundoLote.size());
        assertTrue(segundoLote.get(0).getId() > ultimoId);
    }
}
