package com.example.backend.dto;

import java.time.OffsetDateTime;

import com.example.backend.Enums.Prioridade;
import com.example.backend.Enums.Situacao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Prioridade prioridade;
    private Situacao situacao;
    private LocalDate dataPrevistaConclusao;
    private OffsetDateTime dataCriacao;

}
