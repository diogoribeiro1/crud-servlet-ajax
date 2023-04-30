
$(document).ready(function () {
    getAll();
});

function getAll() {
    $.ajax({
        url: "/crud-jsp/controller",
        type: 'GET',
        success: function (result) {

            var listaPadrao = JSON.parse(result);

            listarEventos(listaPadrao);
        },
        error: function (error) {
            alert(error);
        }
    })
}


function listarEventos(lista) {

    var tbody = $("#tbody");
    tbody.empty();

    lista.map((evento) => {
        tbody.append("<tr> <div class='row'> <td>" + evento.id + "</td> <td>" + evento.nome + "</td> <td>" + evento.data + " </td> <td>" + evento.local + " </td> <td><button type=\"button\" class=\"btn btn-danger\" onclick=\"deletarEvento(" + evento.id + ")\">Delete</button></td> <td><button type=\"button\" class=\"btn btn-primary\" onclick=\"editarEvento(" + evento.id + ")\" data-bs-toggle=\"modal\" data-bs-target=\"#editarModal\">Edit</button></td> </div></tr>");
    })
}

$(function () {
    $("#btnSalvar").click(function () {

        var nome = document.getElementById("inputNome").value;
        var dataInput = document.getElementById("inputData").value;
        var local = document.getElementById("inputLocal").value;
        var action = 'POST';

        $.ajax({
            url: "/crud-jsp/controller",
            type: 'POST',
            data: JSON.stringify({ nome, dataInput, local, action }),
            contentType: 'application/json',
            success: function (data, textStatus, xhr) {

                Swal.fire({
                    icon: 'success',
                    title: 'Salvo',
                    text: 'Salvo com sucesso!',
                    timer: 1500
                })

                document.getElementById("inputNome").value = '';
                document.getElementById("inputData").value = '';
                document.getElementById("inputLocal").value = '';

                getAll();

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
            url: "/crud-jsp/controller/"+id+"",
            type: 'PUT',
            data: JSON.stringify({nome, dataInput, local, action }),
            success: function (data, textStatus, xhr) {

                Swal.fire({
                    icon: 'success',
                    title: 'Editado',
                    text: 'Editado com sucesso!',
                    timer: 1500
                })

                document.getElementById("inputNomeEditar").value = '';
                document.getElementById("inputDataEditar").value = '';
                document.getElementById("inputLocalEditar").value = '';

                getAll();
            },
            error: function () {
                alert('error');
            }
        })
    })
}

function deletarEvento(id) {

    Swal.fire({

        title: 'Deseja realmente deletar?',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Deletar',
        denyButtonText: `Nao deletar`,

    }).then((result) => {

        if (result.isConfirmed) {

            var jsonId = JSON.parse(id);
            var action = 'DELETE';

            var data = {
                jsonId: jsonId,
                action: action
            };

            $.ajax({
                url: "/crud-jsp/controller/"+id+"",
                type: 'DELETE',
                data: JSON.stringify(data),
                success: function () {

                    Swal.fire('Deletado!', '', 'sucesso')

                    getAll();
                },
                error: function () {
                    alert('error');
                }
            })

        } else if (result.isDenied) {
            Swal.fire('Nao deletado', '', 'info')
        }
    })
}

function getEventoById(id) {

    var action = 'GetById'
    $.ajax({
        url: "/crud-jsp/controller",
        type: 'GET',
        data: { id, action },
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