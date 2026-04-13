package br.com.treinamento.despesaspessoais.model

import java.util.Date

data class Sonho(
    val titulo: String,
    val dataInicial: Date,
    val dataFinal: Date,
    val valor: Double
)
