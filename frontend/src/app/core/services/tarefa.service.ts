
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Tarefa, Prioridade, Situacao } from '../models/tarefa.model';

interface TarefaPageResponse {
  content: Tarefa[];
  totalElements: number;
}


@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  private apiUrl = 'http://localhost:8080/tarefas'; 

  constructor(private http: HttpClient) {}

  listarTarefas(
    cursor?: number,
    limit: number = 10,
    nome?: string,
    prioridade?: Prioridade,
    situacao?: Situacao
  ): Observable<TarefaPageResponse> {

    let params = new HttpParams()
      .set('limit', limit.toString());

    if (cursor != null) params = params.set('cursor', cursor.toString());
    if (nome) params = params.set('nome', nome);
    if (prioridade) params = params.set('prioridade', prioridade);
    if (situacao) params = params.set('situacao', situacao);

    return this.http.get<TarefaPageResponse>(this.apiUrl, { params });
    }
    
    obterTarefaPorId(id: number | string): Observable<Tarefa> {
      return this.http.get<Tarefa>(`${this.apiUrl}/${id}`);
    }
    
    atualizarTarefa(id: number | string, tarefa: Partial<Tarefa>) {
      return this.http.put<Tarefa>(`${this.apiUrl}/${id}`, tarefa);
    }

  criarTarefa(tarefa: Partial<Tarefa>) {
    return this.http.post<Tarefa>(this.apiUrl, tarefa);
  }

  excluirTarefa(id: number | string) {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  concluirTarefa(id: number | string) {
    return this.http.patch<Tarefa>(`${this.apiUrl}/${id}/concluir`, {});
  }

  pendenteTarefa(id: number | string) {
    return this.http.patch<Tarefa>(`${this.apiUrl}/${id}/pendente`, {});
  }
}
