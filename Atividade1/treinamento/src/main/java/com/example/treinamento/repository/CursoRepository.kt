package com.example.treinamento.repository

import com.example.treinamento.model.Curso

class CursoRepository {

    private var cursos = mutableListOf<Curso>()
    private var i : Int = 1

    //funcao que cadastra curso
    fun save(curso : Curso) : Curso {
        if(curso.id == 0) {
            curso.id = i++
            cursos.add(curso)
        } else {
            var index = cursos.indexOfFirst { it.id == curso.id }
            if (index >= 0) cursos[index] = curso else cursos.add(curso)
        }
        return curso
    }

    //funcao que busca curso por id
    fun findById(id : Int) : Curso ? = cursos.firstOrNull() { it.id == id}

    //funcao que altera curso cadastrado
    fun update(id : Int, atualizar : (Curso) -> Unit) : Boolean {
        var c = findById(id) ?: return false
        atualizar(c)
        return true
    }

    // funcao que lista cursos
    fun findAll() : List<Curso> = cursos.toList()

    // funcao que pesquisa curso por nome
    fun findByName(termo : String) : List<Curso> =
        cursos.filter { it.nomeCurso.contains(termo, ignoreCase = true) }

    // Funcao que remove curso da lista
    fun delete(id : Int ) : Boolean = cursos.removeIf {it.id == id}

}