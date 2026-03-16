package com.example.treinamento.model

data class Curso (
    var id : Int  = 0,
    var nomeCurso : String,
    var descricao : String ? = null,
    var cargaHoraria : Int,
    var professor : String ? = null,
    var vagas : Int ? = null
)


