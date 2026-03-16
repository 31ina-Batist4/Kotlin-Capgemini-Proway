package com.example.treinamento.service

import com.example.treinamento.model.Curso
import com.example.treinamento.repository.CursoRepository
import com.example.treinamento.util.Validators

class CursoService(private val repo : CursoRepository) {

    fun cadastrar(
        nomeCurso : String,
        descricao : String?,
        cargaHoraria : Int,
        professor : String?,
        vagas : Int?
    ) : Curso {
        validarNome(nomeCurso)
        validarCargaHoraria(cargaHoraria)
        validarDescricao(descricao)
        validarProfessor(professor)
        validarVagas(vagas)

        val curso = Curso(
            nomeCurso = nomeCurso.trim(),
            descricao = descricao?.trim(),
            cargaHoraria = cargaHoraria,
            professor = professor?.trim(),
            vagas = vagas
        )
        return repo.save(curso)
    }

    fun listar() : List<Curso> = repo.findAll()

    fun pesquisarPorId(id : Int)  = repo.findById(id)

    fun pesquisarPorNome(termo : String) = repo.findByName(termo.trim())

    fun alterar(
        id: Int,
        novoNome: String?,
        novaDescricao: String?,
        novaCargaHoraria: Int?,
        novoProfessor: String?,
        novasVagasRaw: String?
    ) : Boolean {
        return repo.update(id) { curso ->
            novoNome?.let {
                validarNome(it)
                curso.nomeCurso = it.trim()
            }
            novaDescricao?.let {
                if (it.isBlank()) {
                    curso.descricao = null
                } else {
                    validarDescricao(it)
                    curso.descricao = it.trim()
                }
            }
            novaCargaHoraria?.let {
                validarCargaHoraria(it)
                curso.cargaHoraria = it
            }
            novoProfessor?.let {
                if (it.isBlank()) {
                    curso.professor = null
                } else {
                    validarProfessor(it)
                    curso.professor = it.trim()
                }
            }
            novasVagasRaw?.let { raw ->
                if (raw.isBlank()) {
                    curso.vagas = null
                } else {
                    val v = raw.toIntOrNull()
                        ?: throw IllegalArgumentException("Vagas deve ser um número inteiro.")
                    validarVagas(v)
                    curso.vagas = v
                }
            }
        }
    }

    fun remover(id: Int): Boolean = repo.delete(id)

    // --------- Validações ---------

    private fun validarNome(nome: String) {
        require(Validators.isNomeValido(nome)) {
            "Nome inválido. Use ao menos 3 caracteres (letras e espaços)."
        }
    }

    private fun validarDescricao(desc: String?) {
        if (desc == null) return
        require(desc.length <= 20) { "Descrição muito longa (máximo 20 caracteres)." }
    }

    private fun validarProfessor(prof: String?) {
        if (prof == null) return
        require(Validators.isNomeValido(prof)) {
            "Nome de professor inválido. Use letras e espaços (mínimo 3)."
        }
    }

    private fun validarCargaHoraria(ch: Int) {
        require(ch in 1..2000) {
            "Carga horária inválida. Informe um número entre 1 e 2000 horas."
        }
    }

    private fun validarVagas(vagas: Int?) {
        if (vagas == null) return
        require(vagas in 1..30) {
            "Vagas inválidas. Informe um inteiro entre 1 e 30."
        }
    }
}