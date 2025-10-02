import { Route } from '@angular/router';
import { ListTarefas } from './features/tarefas/list-tarefas/list-tarefas';
import { FormTarefas } from './features/tarefas/form-tarefas/form-tarefas';

export const routes: Route[] = [
  { path: '', redirectTo: 'tarefas', pathMatch: 'full' },
  { path: 'tarefas', component: ListTarefas },
  { path: 'tarefas/novo', component: FormTarefas },
  { path: 'tarefas/:id/editar', component: FormTarefas },
];
