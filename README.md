# Gerenciador de Tarefas

## Visão Geral

API para gerenciar tarefas, incluindo criação, atualização, exclusão e recuperação de tarefas, além de gerenciar categorias e usuários.

## URL Base

http://localhost:8080/

## Autenticação

- **Método**: Autenticação baseada em token.
- Inclua o token no cabeçalho `Authorization` das suas requisições:

## Endpoints

### Tarefas

#### Criar Tarefa
- **Endpoint**: `/tasks`
- **Método**: POST
- **Corpo da Requisição** (JSON):
  ```json
  {
      "title": "Finalizar documentação",
      "description": "Completar a documentação da API",
      "due_date": "2025-02-01T00:00:00",
      "priority": "HIGH",
      "status": "PENDING",
      "category_id": 1,
      "user_id": 2
  }

## Listar Tarefas
- **Endpoint**: `/tasks`
- **Método**: GET

---

## Categorias

### Listar Categorias
- **Endpoint**: `/categories`
- **Método**: GET

---

## Usuários

### Registrar Usuário
- **Endpoint**: `/users/register`
- **Método**: POST
- **Corpo da Requisição** (JSON):
  ```json
  {
      "name": "João Silva",
      "email": "joao.silva@example.com",
      "password": "senhaSegura"
  }
  
