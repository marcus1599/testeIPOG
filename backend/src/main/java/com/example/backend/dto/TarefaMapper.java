package com.example.backend.dto;

import com.example.backend.Entities.Tarefa;

public class TarefaMapper {

    public static TarefaResponseDTO toDTO(Tarefa tarefa) {
        TarefaResponseDTO dto = new TarefaResponseDTO();
        dto.setId(tarefa.getId());
        dto.setNome(tarefa.getNome());
        dto.setDescricao(tarefa.getDescricao());
        dto.setPrioridade(tarefa.getPrioridade());
        dto.setSituacao(tarefa.getSituacao());
        dto.setDataPrevistaConclusao(tarefa.getDataPrevistaConclusao());
        dto.setDataCriacao(tarefa.getDataCriacao());
        return dto;
    }

    public static Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome(dto.getNome());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setDataPrevistaConclusao(dto.getDataPrevistaConclusao());
        return tarefa;
    }
}
