<style>
  details {
    border: 1px solid #ccc;
    border-radius: 4px;
    margin: 0.5em 0;
    padding: 0.5em;
  }

  details[open] {
    background-color: #f0f0f0;
  }
</style>

# CRUD Servlet Ajax

Este é um projeto de CRUD simples utilizando Java no lado do servidor e a arquitetura REST, sem a utilização de frameworks. Já no lado do cliente, é utilizado Ajax com jQuery para realizar requisições assíncronas à API e atualizar o conteúdo da página sem precisar recarregá-la completamente.

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
        "data":"20/12/2025",
        "local":"Local do Evento"
    }    
```
## Exemplo de Resposta

HTTP/1.1 201 CREATED

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