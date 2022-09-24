$(document).ready(function () {
    $.ajax({
        url: "/crud-jsp/controller",
        type: 'GET',
        success: function (result) {
            var lista = JSON.parse(result);
            listarEventos(lista);
        },
        error: function (error) {
            alert(error);
        }
    })
});

function listarEventos(lista) {

    var tbody = document.getElementById("tbody");

    lista.map((evento) => {
        $("#tbody").append("<tr> <div class='row'> <td>" + evento.id + "</td> <td>" + evento.nome + "</td> <td>" + evento.data + " </td> <td>" + evento.local + " </td> <td><button type=\"button\" class=\"btn btn-danger\" onclick=\"deletarEvento(" + evento.id + ")\">Delete</button></td> <td><button type=\"button\" class=\"btn btn-primary\" onclick=\"editarEvento(" + evento.id + ")\" data-bs-toggle=\"modal\" data-bs-target=\"#editarModal\">Edit</button></td> </div></tr>");
    })
}

$(function () {
    $('form[name = "formSave"]').submit(function (event) {

        event.preventDefault();

        var nome = document.getElementById("inputNome").value;
        var dataInput = document.getElementById("inputData").value;
        var local = document.getElementById("inputLocal").value;
        var action = 'POST';

        $.ajax({
            url: "/crud-jsp/controller",
            type: 'POST',
            data: {nome, dataInput, local, action},
            success: function () {
                alert('Salvo com Sucesso!')
                document.location.reload(true);
            },
            error: function (error) {
                alert(error);
            }
        })
    })
})

function editarEvento(id) {

    getEventoById(id);

    $('form[name = "formEdit"]').submit(function (event) {

        event.preventDefault();

        var nome = document.getElementById('inputNomeEditar').value;
        var dataInput = document.getElementById('inputDataEditar').value;
        var local = document.getElementById('inputLocalEditar').value;
        var action = 'PUT'

        $.ajax({
            url: "/crud-jsp/controller",
            type: 'POST',
            data: {id, nome, dataInput, local, action},
            success: function () {
                alert('Editado com Sucesso!')
                document.location.reload(true);
            },
            error: function () {
                alert('error');
            }
        })
    })
}

function deletarEvento(id) {

    result = confirm("Deseja realmente apagar?");
    if (result) {
        var jsonId = JSON.parse(id);
        var action = 'DELETE';

        $.ajax({
            url: "/crud-jsp/controller",
            type: 'POST',
            data: {jsonId, action},
            success: function () {
                alert('Deletado com Sucesso!')
                document.location.reload(true);
            },
            error: function () {
                alert('error');
            }
        })
    }

}

function getEventoById(id) {

    var action = 'GetById'
    $.ajax({
        url: "/crud-jsp/controller",
        type: 'GET',
        data: {id, action},
        success: function (result) {
            evento = JSON.parse(result);
            setarInputs([evento]);
        },
        error: function () {
            alert('error');
        }
    })

}

function setarInputs(evento) {

    evento.map(function (value) {
        document.getElementById('inputNomeEditar').value = value.nome;
        document.getElementById('inputDataEditar').value = value.data;
        document.getElementById('inputLocalEditar').value = value.local;
    });
    $('#editarModal').modal('show');

}