package com.example.backend.dto;

import java.util.List;

public record TarefaPageResponseDTO(
        List<TarefaResponseDTO> content,
        long totalElements) {
}
