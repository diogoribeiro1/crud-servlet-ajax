<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Eventos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h1>Event</h1>
    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#salvarModal">
        Save Event
    </button>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nome</th>
            <th scope="col">Data</th>
            <th scope="col">Local</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</div>

<div class="modal" tabindex="-1" id="editarModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Editar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form METHOD="POST" class="row g-3">
                    <div class="col-md-6">
                        <label for="inputIdEditar" class="form-label">Id</label>
                        <input type="text" class="form-control" id="inputIdEditar" name="inputIdEditar" readonly>
                    </div>
                    <div class="col-md-6">
                        <label for="inputNomeEditar" class="form-label">Evento</label>
                        <input type="text" class="form-control" id="inputNomeEditar" name="inputNomeEditar">
                    </div>
                    <div class="col-md-6">
                        <label for="inputDataEditar" class="form-label">Data</label>
                        <input type="date" class="form-control" id="inputDataEditar" name="inputDataEditar">
                    </div>
                    <div class="col-12">
                        <label for="inputLocalEditar" class="form-label">Local</label>
                        <input type="text" class="form-control" id="inputLocalEditar" name="inputLocalEditar">
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary" id="btnEditar">Editar</button>
            </div>
            </form>
        </div>
    </div>
</div>

<div class="modal" tabindex="-1" id="salvarModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Salvar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form METHOD="POST" class="row g-3">
                    <div class="col-md-6">
                        <label for="inputNome" class="form-label">Evento</label>
                        <input type="text" class="form-control" id="inputNome" name="inputNome">
                    </div>
                    <div class="col-md-6">
                        <label for="inputData" class="form-label">Data</label>
                        <input type="date" class="form-control" id="inputData" name="inputData">
                    </div>
                    <div class="col-12">
                        <label for="inputLocal" class="form-label">Local</label>
                        <input type="text" class="form-control" id="inputLocal" name="inputLocal">
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                <button id="btnSalvar" type="button" class="btn btn-primary">Salvar</button>
            </div>
            </form>
        </div>
    </div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
<script src= "js/script.js"></script>
</body>
</html>
