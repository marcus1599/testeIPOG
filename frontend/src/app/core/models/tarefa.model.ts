export enum Prioridade {
  BAIXA = 'BAIXA',
  MEDIA = 'MEDIA',
  ALTA = 'ALTA'
}

export enum Situacao {
  ABERTA = 'ABERTA',
  PENDENTE = 'PENDENTE',
  CONCLUIDA = 'CONCLUIDA'
}

export interface Tarefa {
  id: number | string;
  nome: string;
  descricao?: string;
  prioridade: Prioridade;
  situacao: Situacao;
  dataPrevistaConclusao: Date;
  dataCriacao: Date;
}
