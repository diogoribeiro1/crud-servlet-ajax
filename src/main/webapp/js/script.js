$.ajax({
    url: "/crud-jsp/controller",
    type: 'GET',
    success: function (result) {

        lista = JSON.parse(result);
        console.log(lista)
        listarEventos(lista);
    },
    error: function (error) {
        alert(error);
    }
})

function listarEventos(lista){
   tbody = document.getElementById("tbody");
    lista.map((evento) =>{
        $("#tbody").append("<tr> <div class='row'> <td>"+ evento.id +"</td> <td>"+ evento.nome +"</td> <td>"+ evento.data + " </td> <td>" + evento.local + " </td> <td><button type=\"button\" class=\"btn btn-danger\" onclick=\"deletarEvento("+evento.id+")\">Delete</button></td> <td><button type=\"button\" class=\"btn btn-primary\" onclick=\"getEventoById("+evento.id+")\" data-bs-toggle=\"modal\" data-bs-target=\"#editarModal\">Edit</button></td>   </div></tr>");
    })
    // for (let i = 0; i < lista.length; i++) {
    //     $("#tbody").append("<tr> <td>"+ lista[i].id +"</td> </tr>");
    // }

}

$(function (){
    $("#btnSalvar").on("click", function (){

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

// var xhttp = new XMLHttpRequest();
//     xhttp.open("GET", "/crud-jsp/controller");
// xhttp.send();

$(function (){
    $("#btnEditar").on("click", function (){

        var id = document.getElementById("inputIdEditar").value;
        var nome = document.getElementById("inputNomeEditar").value;
        var dataInput = document.getElementById("inputDataEditar").value;
        var local = document.getElementById("inputLocalEditar").value;
        var action = 'PUT';

        $.ajax({
            url: "/crud-jsp/controller",
            type: 'PUT',
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
})

function deletarEvento(id)
{
    result = confirm("Deseja realmente apagar?");
    if (result) {
        var jsonId = JSON.parse(id);
        var action = 'DELETE';

        $.ajax({
            url: "/crud-jsp/controller",
            type: 'DELETE',
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

function getEventoById(id)
{
    var action = 'GetById'
    $.ajax({
        url: "/crud-jsp/controller",
        type: 'GET',
        data: {id, action},
        success: function (result) {
            console.log(result)
            evento = JSON.parse(result);
            setarInputs([evento]);
        },
        error: function () {
            alert('error');
        }
    })
}

function setarInputs(evento)
{
    evento.map(function (value){
        document.getElementById('inputIdEditar').value = value.id;
        document.getElementById('inputNomeEditar').value = value.nome;
        document.getElementById('inputDataEditar').value = value.data;
        document.getElementById('inputLocalEditar').value = value.local;
    });
    $('#editarModal').modal('show');
}