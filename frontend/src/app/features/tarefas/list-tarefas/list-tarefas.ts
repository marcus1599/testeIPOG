
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TarefaService } from '../../../core/services/tarefa.service';
import { Tarefa, Prioridade, Situacao } from '../../../core/models/tarefa.model';
import { SHARED_MODULES } from '../../../shared/shared';

@Component({
  selector: 'app-list-tarefas',
  templateUrl: './list-tarefas.html',
  imports: [...SHARED_MODULES],
  styleUrls: ['./list-tarefas.scss'],
  standalone: true
})
export class ListTarefas implements OnInit {
  tarefas: Tarefa[] = [];
  totalRecords: number = 0;

  prioridades: SelectOption[] = [
    { label: 'Baixa', value: Prioridade.BAIXA },
    { label: 'Média', value: Prioridade.MEDIA },
    { label: 'Alta', value: Prioridade.ALTA }
  ];

  situacoes: SelectOption[] = [
    { label: 'Aberta', value: Situacao.ABERTA },
    { label: 'Pendente', value: Situacao.PENDENTE },
    { label: 'Concluída', value: Situacao.CONCLUIDA }
  ];

  filtroForm = new FormGroup({
    nomeFiltro: new FormControl(''),
    prioridade: new FormControl(null),
    situacao: new FormControl(null)
  });

  constructor(private tarefaService: TarefaService) {}

  ngOnInit(): void {
    this.carregarTarefas();
  }

  carregarTarefas(cursor?: number, limit: number = 10) {
    const { nomeFiltro, prioridade, situacao } = this.filtroForm.value;

   this.tarefaService
      .listarTarefas(
        cursor,
        limit,
        nomeFiltro || undefined, 
        prioridade|| undefined,
        situacao|| undefined
      )
      .subscribe({
        next: res => {
          this.tarefas = res.content;
          this.totalRecords = res.totalElements;
        },
        error: err => console.error('Erro ao carregar tarefas', err)
      });
  }

  limparFiltros() {
    this.filtroForm.reset();
    this.carregarTarefas();
  }

  editar(tarefa: Tarefa) {
    console.log('Editar tarefa', tarefa);
  }

  concluir(tarefa: Tarefa) {
    this.tarefaService.concluirTarefa(tarefa.id).subscribe(() => this.carregarTarefas());
  }

  pendente(tarefa: Tarefa) {
    this.tarefaService.pendenteTarefa(tarefa.id).subscribe(() => this.carregarTarefas());
  }

  excluir(tarefa: Tarefa) {
    this.tarefaService.excluirTarefa(tarefa.id).subscribe(() => this.carregarTarefas());
  }
}


interface SelectOption {
  label: string;
  value: any;
}
