import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { TarefaService } from '../../../core/services/tarefa.service';
import { Tarefa } from '../../../core/models/tarefa.model';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

import { DatePickerModule } from 'primeng/datepicker';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-form-tarefa',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    DatePickerModule,
    SelectModule,
    ToastModule
  ],
  templateUrl: './form-tarefas.html',
  providers: [MessageService]
})
export class FormTarefas implements OnInit {
  form: FormGroup;
  prioridades = ['BAIXA', 'MEDIA', 'ALTA'];
  tarefaId?: number;
  hoje: Date = new Date();

  constructor(
    private fb: FormBuilder,
    private tarefaService: TarefaService,
    public router: Router,
    private route: ActivatedRoute,
    private messageService: MessageService
  ) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      descricao: [''],
      prioridade: ['BAIXA', Validators.required],
      situacao: [{ value: 'ABERTA', disabled: true }],
      dataPrevistaConclusao: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.tarefaId = +id;
        this.loadTarefa(this.tarefaId);
      }
    });
  }

  private loadTarefa(id: number) {
    this.tarefaService.obterTarefaPorId(id).subscribe(tarefa => {
      this.form.patchValue(tarefa);
    });
  }

  save() {
    if (this.form.invalid) {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Formulário inválido!' });
      return;
    }

    const tarefa: Tarefa = this.form.getRawValue();

    const request = this.tarefaId
      ? this.tarefaService.atualizarTarefa(this.tarefaId, tarefa)
      : this.tarefaService.criarTarefa(tarefa);

    request.subscribe(() => {
      this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Tarefa salva!' });
      this.router.navigate(['/tarefas']);
    });
  }
}
