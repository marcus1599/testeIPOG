package com.example.backend.dto;

import com.example.backend.Enums.Prioridade;
import com.example.backend.Enums.Situacao;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.OffsetDateTime;


public record TarefaDTO(
        Long id,
        @NotBlank(message = "nome é obrigatório") String nome,
        String descricao,
        @NotNull(message = "prioridade é obrigatória") Prioridade prioridade,
        Situacao situacao,
        @NotNull(message = "dataPrevistaConclusao é obrigatória") @FutureOrPresent(message = "dataPrevistaConclusao não pode ser anterior à data atual") LocalDate dataPrevistaConclusao,
        OffsetDateTime dataCriacao) {
}
