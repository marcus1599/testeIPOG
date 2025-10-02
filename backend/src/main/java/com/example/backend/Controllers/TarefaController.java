package com.example.backend.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Services.TarefaService;
import com.example.backend.dto.TarefaMapper;
import com.example.backend.dto.TarefaRequestDTO;
import com.example.backend.dto.TarefaResponseDTO;
import com.example.backend.Entities.Tarefa;
import com.example.backend.Enums.Prioridade;
import com.example.backend.Enums.Situacao;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Gerenciamento de tarefas")
public class TarefaController {

    private final TarefaService service;

    @Operation(summary = "Listar tarefas com paginação por cursor e filtros")
    @GetMapping
    public List<TarefaResponseDTO> listar(
            @Parameter(description = "ID da última tarefa carregada (cursor)") @RequestParam(required = false) Long cursor,

            @Parameter(description = "Quantidade máxima de registros por página") @RequestParam(defaultValue = "10") int limit,

            @Parameter(description = "Filtro por nome (contém)") @RequestParam(required = false) String nome,

            @Parameter(description = "Filtro por prioridade (BAIXA, MEDIA, ALTA)") @RequestParam(required = false) Prioridade prioridade,

            @Parameter(description = "Filtro por situação (ABERTA, PENDENTE, CONCLUIDA)") @RequestParam(required = false) Situacao situacao) {
        return service.listarComCursor(cursor, limit, nome, prioridade, situacao)
                .stream()
                .map(TarefaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Criar nova tarefa")
    @PostMapping
    public TarefaResponseDTO criar(@Valid @RequestBody TarefaRequestDTO tarefaDTO) {
        Tarefa tarefa = TarefaMapper.toEntity(tarefaDTO);
        Tarefa salva = service.salvar(tarefa);
        return TarefaMapper.toDTO(salva);
    }

    @Operation(summary = "Excluir tarefa pelo ID")
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @Operation(summary = "Marcar tarefa como concluída")
    @PatchMapping("/{id}/concluir")
    public TarefaResponseDTO concluir(@PathVariable Long id) {
        return TarefaMapper.toDTO(service.concluir(id));
    }

    @Operation(summary = "Marcar tarefa como pendente")
    @PatchMapping("/{id}/pendente")
    public TarefaResponseDTO pendente(@PathVariable Long id) {
        return TarefaMapper.toDTO(service.pendente(id));
    }
}
