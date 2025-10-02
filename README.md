🚀 Como Executar

### Pré-requisitos

- Node.js >= 20
- Angular CLI >= 20
- Java 17
- Maven 4

### 1️⃣ Back-end

1. Entre na pasta do backend:

```bash
cd backend
Build do projeto:

bash

mvn clean install
Executar a aplicação:

bash

mvn spring-boot:run
A API será disponibilizada em: http://localhost:8080

Observação: O endpoint POST /tarefas apresenta bug relacionado ao cursor de paginação; pode retornar erro 500.

2️⃣ Front-end
Entre na pasta do frontend:

bash

cd frontend
Instale as dependências:

bash

npm install

Execute a aplicação:

bash

ng serve --open
O front-end estará disponível em: http://localhost:4200

Observação: Temporariamente o projeto está sem PrimeNG; o SCSS atual garante aparência básica das telas de tarefas e formulários.

📝 Funcionalidades Implementadas
Listagem de tarefas com paginação

Criação e edição de tarefas via formulário

Filtros por nome, prioridade e situação

Exclusão e conclusão de tarefas

Feedback visual via toast (PrimeNG temporariamente removido)

Estrutura modular com Angular Standalone Components

SCSS básico aplicado para aparência consistente

⚠️ Bugs / Pendências Conhecidas
PrimeNG temporariamente removido do front-end por problemas de importação

Backend retorna erro 500 ao criar tarefas devido ao cursor de paginação

Melhorias de UI/UX ainda necessárias

Autenticação ainda não implementada
```
