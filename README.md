# Gerenciador de Tarefas

## Visão Geral

API para gerenciar tarefas, incluindo criação, atualização, exclusão e recuperação de tarefas, além de gerenciar categorias e usuários.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- SQL

## URL Base

http://localhost:8080/api/v1

## Autenticação

- **Método**: Autenticação baseada em token.
- Inclua o token no cabeçalho `Authorization` das suas requisições:

## Endpoints

### Tarefas

#### Criar Tarefa
- **Endpoint**: `/tasks/add`
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

# API de Gerenciamento de Tarefas

## Tarefas

### Listar Tarefas
- **Endpoint:** `/tasks/list`
- **Método:** `GET`

### Atualizar Tarefa
- **Endpoint:** `/tasks/update/{id}`
- **Método:** `PUT`
- **Corpo da Requisição (JSON):**
  ```json
  {
      "id": 1,
      "title": "Atualizar documentação",
      "description": "Atualizar a documentação da API",
      "due_date": "2025-03-01T00:00:00",
      "priority": "MEDIUM",
      "status": "IN_PROGRESS",
      "category": {
          "id": 2
      }
  }
  ```

### Deletar Tarefa
- **Endpoint:** `/tasks/delete/{id}`
- **Método:** `DELETE`

---

## Categorias

### Listar Categorias
- **Endpoint:** `/categories`
- **Método:** `GET`

---

## Usuários

### Registrar Usuário
- **Endpoint:** `/users/register`
- **Método:** `POST`
- **Corpo da Requisição (JSON):**
  ```json
  {
      "name": "João Silva",
      "email": "joao.silva@example.com",
      "password": "senhaSegura"
  }
  ```

### Autenticar Usuário
- **Endpoint:** `/users/login`
- **Método:** `POST`
- **Corpo da Requisição (JSON):**
  ```json
  {
      "email": "joao.silva@example.com",
      "password": "senhaSegura"
  }
  ```
