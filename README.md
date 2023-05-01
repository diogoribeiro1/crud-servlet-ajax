# CRUD Servlet Ajax

Este é um projeto de CRUD simples utilizando Java no lado do servidor e a arquitetura REST, sem a utilização de frameworks. Já no lado do cliente, é utilizado Ajax com jQuery para realizar requisições assíncronas à API e atualizar o conteúdo da página sem precisar recarregá-la completamente.

## Setup Project

Execute no terminal

```shell
    mvn tomcat6:run -f "pom.xml" 
```

## Endpoints

|   Endpoints   |  Query Parameters  |    Verb    |
| :---         |     :---:      |          ---: |
| /crud-jsp/controller   |      | GET    |
| /crud-jsp/controller/{id}   |      | GET    |
| /crud-jsp/controller     | Action, nome       | POST    |
| /crud-jsp/controller/{id}   |      | PUT    |
| /crud-jsp/controller/{id}   |      | DELETE |

`/crud-jsp/controller`

## Descrição

Retorna os detalhes de um usuário específico.

## Parâmetros de Entrada

- `id` (obrigatório): ID do usuário.

## Códigos de Resposta

- `200`: Usuário encontrado com sucesso.
- `404`: Usuário não encontrado.

## Exemplo de Requisição

GET /users/123 HTTP/1.1
Host: example.com

## Exemplo de Resposta

HTTP/1.1 200 OK
Content-Type: application/json

{
"id": 123,
"name": "João da Silva",
"email": "joao.silva@example.com"
}

## Tecnologias Utilizadas

- Java 11
- Ajax
- jQuery
- Servlet
- MySQL
- Lombok
- HTML
- CSS
- JavaScript
- Bootstrap


## Funcionalidades do Sistema

- Listar Evento;
- Inserir Evento;
- Editar Evento;
- Deletar Evento;
