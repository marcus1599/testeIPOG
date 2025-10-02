package com.example.backend.dto;

import java.util.List;

import com.example.backend.Entities.Tarefa;

public record TarefaPageDTO(
        List<Tarefa> content,
        long totalElements) {
}