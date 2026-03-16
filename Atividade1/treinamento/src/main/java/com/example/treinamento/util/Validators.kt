package com.example.treinamento.util

object Validators {

    private val nomeRegex = Regex("^[\\p{L} ]{3,}$")

    fun isNomeValido(nome: String): Boolean =
        nome.trim().matches(nomeRegex)

}