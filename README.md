# CRUD Servlet Ajax

Este é um projeto que implementa operações CRUD utilizando Java no lado do servidor e a arquitetura REST. Para isso, é utilizado o Servlet e Junit em conjunto com Mockito para criar testes unitários confiáveis. Por outro lado, do lado do cliente, as requisições assíncronas à API são feitas por meio de Ajax com jQuery, permitindo que o conteúdo da página seja atualizado sem precisar recarregá-la completamente.

## Tecnologias Utilizadas

- Java 11
- Servlet
- Lombok
- GSON
- Jackson
- JUnit
- Mockito
- HTML
- CSS
- JavaScript
- Ajax
- jQuery
- Bootstrap
- MySQL

## Funcionalidades do Sistema

- Listar Evento;
- Inserir Evento;
- Editar Evento;
- Deletar Evento;

## Setup Project

Execute no terminal

```shell
    mvn tomcat6:run -f "pom.xml"    
```

## Payload

```json
    {
        "id": 1,
        "nome":"Meu Evento",
        "data":"20/12/2025",
        "local":"Local do Evento"
    } 
```

## Endpoints

|   Endpoints   |  Query Parameters  |    Verb    |
| :---         |     :---:      |          ---: |
| /crud-jsp/event        |   *  | GET    |
| /crud-jsp/event/{id}   |   *  | GET    |
| /crud-jsp/event        |   *  | POST   |
| /crud-jsp/event/{id}   |   *  | PUT    |
| /crud-jsp/event/{id}   |   *  | DELETE |


<details>
  <summary>GET ALL - /crud-jsp/event </summary>
  
## Descrição

Retorna todos os eventos.

## Códigos de Resposta

- `200`: Eventos encontrado com sucesso.

## Exemplo de Requisição

GET /crud-jsp/event HTTP/1.1
Host: example.com

## Exemplo de Resposta

HTTP/1.1 200 OK
Content-Type: application/json
```json
    [
        {
        "id": 1,
        "nome":"Meu Evento",
        "data":"20/12/2025",
        "local":"Local do Evento"
        },
        {
        "id": 2,
        "nome":"Meu Evento",
        "data":"20/12/2025",
        "local":"Local do Evento"
        }  
    ]
```
  
</details>

<details>
  <summary>GET BY ID - /crud-jsp/event/{id} </summary>
  
## Descrição

Retorna um evento.

## Parâmetros de Entrada

- `id` (obrigatório): ID do evento.

## Códigos de Resposta

- `200`: Evento encontrado com sucesso.

## Exemplo de Requisição

GET /crud-jsp/event/1 HTTP/1.1
Host: example.com

## Exemplo de Resposta

HTTP/1.1 200 OK
Content-Type: application/json
```json
    {
        "id": 1,
        "nome":"Meu Evento",
        "data":"20/12/2025",
        "local":"Local do Evento"
    }    
```
  
</details>

<details>
  <summary>CREATE - /crud-jsp/event </summary>
  
## Descrição

Cria um evento.

## Códigos de Resposta

- `201`: Evento criado com sucesso.

## Exemplo de Requisição

POST /crud-jsp/event HTTP/1.1
Host: example.com
Content-Type: application/json
```json
    {
        "nome":"Meu Evento",
        "dataInput":"20/12/2025",
        "local":"Local do Evento"
    }    
```
## Exemplo de Resposta

HTTP/1.1 201 CREATED
```json
    {
        "id": 1,
        "nome":"Meu Evento",
        "dataInput":"20/12/2025",
        "local":"Local do Evento"
    }    
```
</details>

<details>
  <summary>UPDATE - /crud-jsp/event/{id} </summary>
  
## Descrição

Edita um evento.

## Parâmetros de Entrada

- `id` (obrigatório): ID do evento.

## Códigos de Resposta

- `200`: Evento editado com sucesso.

## Exemplo de Requisição

PUT /crud-jsp/event/1 HTTP/1.1
Host: example.com
Content-Type: application/json
```json
    {
        "nome":"Meu Novo Evento",
        "data":"12/02/2026",
        "local":"Novo Local do Evento"
    }    
```
## Exemplo de Resposta

HTTP/1.1 200 OK

```json
    {
        "id": 1,
        "nome":"Meu Novo Evento",
        "data":"12/02/2026",
        "local":"Novo Local do Evento"
    }    
```
  
</details>

<details>
  <summary>DELETE - /crud-jsp/event/{id} </summary>
  
## Descrição

Deleta um evento.

## Parâmetros de Entrada

- `id` (obrigatório): ID do evento.

## Códigos de Resposta

- `204`: Evento deletado com sucesso.

## Exemplo de Requisição

DELETE /crud-jsp/event/1 HTTP/1.1
Host: example.com

## Exemplo de Resposta

HTTP/1.1 204 NO CONTENT
  
</details>