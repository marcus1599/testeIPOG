package com.example.backend.Repositories;

import com.example.backend.Entities.Tarefa;
import com.example.backend.Enums.Prioridade;
import com.example.backend.Enums.Situacao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM Tarefa t " +
            "WHERE (:cursor IS NULL OR t.id > :cursor) " +
            "AND (:nome IS NULL OR LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
            "AND (:prioridade IS NULL OR t.prioridade = :prioridade) " +
            "AND (:situacao IS NULL OR t.situacao = :situacao) " +
            "ORDER BY t.id ASC")
    List<Tarefa> findNextPage(
            @Param("cursor") Long cursor,
            @Param("nome") String nome,
            @Param("prioridade") Prioridade prioridade,
            @Param("situacao") Situacao situacao,
            Pageable pageable);
}
