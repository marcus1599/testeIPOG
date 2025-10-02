package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.backend.Entities.Tarefa;
import com.example.backend.Enums.Prioridade;
import com.example.backend.Enums.Situacao;
import com.example.backend.Repositories.TarefaRepository;
import com.example.backend.services.TarefaService;

@SpringBootTest
class TarefaServiceTest {

    @Autowired
    private TarefaService service;

    @Autowired
    private TarefaRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void deveSalvarTarefaComSituacaoAberta() {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Estudar Java");
        tarefa.setPrioridade(Prioridade.ALTA);
        tarefa.setDataPrevistaConclusao(LocalDate.now().plusDays(1));

        Tarefa salva = service.salvar(tarefa);

        assertNotNull(salva.getId());
        assertEquals(Situacao.ABERTA, salva.getSituacao());
        assertNotNull(salva.getDataCriacao());
    }

    @Test
    void deveLancarErroSeDataPrevistaAnteriorAHoje() {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Testar");
        tarefa.setPrioridade(Prioridade.BAIXA);
        tarefa.setDataPrevistaConclusao(LocalDate.now().minusDays(1));

        assertThrows(IllegalArgumentException.class, () -> service.salvar(tarefa));
    }

    @Test
    void deveMarcarComoConcluida() {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Estudar");
        tarefa.setPrioridade(Prioridade.MEDIA);
        tarefa.setDataPrevistaConclusao(LocalDate.now().plusDays(1));
        Tarefa salva = service.salvar(tarefa);

        Tarefa concluida = service.concluir(salva.getId());
        assertEquals(Situacao.CONCLUIDA, concluida.getSituacao());
    }

    @Test
    void deveMarcarComoPendente() {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Estudar");
        tarefa.setPrioridade(Prioridade.MEDIA);
        tarefa.setDataPrevistaConclusao(LocalDate.now().plusDays(1));
        Tarefa salva = service.salvar(tarefa);

        Tarefa pendente = service.pendente(salva.getId());
        assertEquals(Situacao.PENDENTE, pendente.getSituacao());
    }
}
